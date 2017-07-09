package com.takwolf.android.repause;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public final class Repause implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "Repause";

    private Repause() {}

    private static Repause singleton;

    public static void init(Application application) {
        if (singleton == null) {
            singleton = new Repause();
            application.registerActivityLifecycleCallbacks(singleton);
        } else {
            Log.w(TAG, TAG + " has been initialized.");
        }
    }

    private static void checkInit() {
        if (singleton == null) {
            throw new RuntimeException(TAG + " has not been initialized.");
        }
    }

    public static void registerListener(Listener listener) {
        checkInit();
        synchronized (singleton.listenerList) {
            singleton.listenerList.add(listener);
        }
    }

    public static void unregisterListener(Listener listener) {
        checkInit();
        synchronized (singleton.listenerList) {
            singleton.listenerList.remove(listener);
        }
    }

    public static boolean isApplicationResumed() {
        checkInit();
        return singleton.active;
    }

    public static boolean isApplicationPaused() {
        return !isApplicationResumed();
    }

    public static void setCheckTime(long checkTime) {
        checkInit();
        singleton.checkTime = checkTime;
    }

    private final List<Listener> listenerList = new ArrayList<>();
    private final Handler handler = new Handler(Looper.getMainLooper());

    private boolean active = false;

    private volatile long checkTime = 100;
    private boolean checking = false;

    private Listener[] collectListeners() {
        synchronized (listenerList) {
            if (listenerList.size() > 0) {
                Listener[] listeners = new Listener[listenerList.size()];
                listenerList.toArray(listeners);
                return listeners;
            } else {
                return null;
            }
        }
    }

    private void dispatchApplicationResumed() {
        Listener[] listeners = collectListeners();
        if (listeners != null) {
            for (Listener listener : listeners) {
                if (listener != null) {
                    listener.onApplicationResumed();
                }
            }
        }
    }

    private void dispatchApplicationPaused() {
        Listener[] listeners = collectListeners();
        if (listeners != null) {
            for (Listener listener : listeners) {
                if (listener != null) {
                    listener.onApplicationPaused();
                }
            }
        }
    }

    private final Runnable checkRunnable = new Runnable() {

        @Override
        public void run() {
            synchronized (checkRunnable) {
                checking = false;
                if (active) {
                    active = false;
                    dispatchApplicationPaused();
                }
            }
        }

    };

    @Override
    public void onActivityResumed(Activity activity) {
        synchronized (checkRunnable) {
            if (checking) {
                handler.removeCallbacks(checkRunnable);
                checking = false;
            }
            if (!active) {
                active = true;
                dispatchApplicationResumed();
            }
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        synchronized (checkRunnable) {
            if (active) {
                handler.removeCallbacks(checkRunnable);
                handler.postDelayed(checkRunnable, checkTime);
                checking = true;
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

    @Override
    public void onActivityDestroyed(Activity activity) {}

    @Override
    public void onActivityStarted(Activity activity) {}

    @Override
    public void onActivityStopped(Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    public interface Listener {

        void onApplicationResumed();

        void onApplicationPaused();

    }

}
