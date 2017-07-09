package com.takwolf.android.repause;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
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


        // TODO


        return false;
    }

    public static boolean isApplicationPaused() {
        return !isApplicationResumed();
    }

    private final List<Listener> listenerList = new ArrayList<>();

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

    @Override
    public void onActivityResumed(Activity activity) {

        // TODO

    }

    @Override
    public void onActivityPaused(Activity activity) {

        // TODO

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
