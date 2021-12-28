# Deprecated

This tool is now deprecated. Please switch to [Lifecycle-Aware Components](https://developer.android.google.cn/topic/libraries/architecture/lifecycle).

# Android - Repause

[![Build Status](https://travis-ci.org/TakWolf/Android-Repause.svg?branch=master)](https://travis-ci.org/TakWolf/Android-Repause)
[![Bintray](https://api.bintray.com/packages/takwolf/maven/Android-Repause/images/download.svg)](https://bintray.com/takwolf/maven/Android-Repause/_latestVersion)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg)](https://android-arsenal.com/api?level=14)
[![License](https://img.shields.io/github/license/TakWolf/Android-Repause.svg)](http://www.apache.org/licenses/LICENSE-2.0)

English | [中文](README-zh.md)

A utility to help listen Android application level resumed or paused.

The application level paused, meaning that all of the `Activity` are paused.

This library implements the listening of the application level resumed and paused by adding a delay check task in the `Activity.onPause ()` callback.

The behavior is similar to iOS's `UIApplicationDelegate`.

However, note that the implementation uses the hack way.

Using this library, make sure you have understood the implementation principle.

Application resumed or paused, is different form application enter foreground or background in concept.

There is another library named [Android-Foreback](https://github.com/TakWolf/Android-Foreback), can listen application enter foreground or background.

If this library is not for you, try another library.

## Usage

### Gradle

``` gradle
implementation 'com.takwolf.android:repause:0.1.1'
```

### Java

Initialize in `Application.onCreate()`, and register a listener:

``` java
public class AppController extends Application implements Repause.Listener {

    @Override
    public void onCreate() {
        super.onCreate();
        Repause.init(this);
        Repause.registerListener(this);
    }

    @Override
    public void onApplicationResumed() {
        // TODO
    }

    @Override
    public void onApplicationPaused() {
        // TODO
    }

}
```

Notice, if register listener in `Activity`, don't forget to unregister to avoid memory leaks.

Also library provides the following api:

``` java
Repause.isApplicationResumed();
Repause.isApplicationPaused();
```

## License

```
Copyright 2017 TakWolf

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
