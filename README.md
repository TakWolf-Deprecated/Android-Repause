# Android - Repause #

[![Build Status](https://travis-ci.org/TakWolf/Android-Repause.svg?branch=master)](https://travis-ci.org/TakWolf/Android-Repause)
[![Download](https://api.bintray.com/packages/takwolf/maven/Android-Repause/images/download.svg)](https://bintray.com/takwolf/maven/Android-Repause/_latestVersion)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg?style=flat)](https://www.android.com)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![License](https://img.shields.io/github/license/TakWolf/Android-Repause.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

A utility to help listen Android application level resumed or paused.

一个用于帮助监听 Android 应用级别恢复或者暂停的工具。

## Usage ##

### Gradle ###

``` gradle
compile 'com.takwolf.android:repause:0.0.1'
```

### Java ###

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

## Author ##

TakWolf

[takwolf@foxmail.com](mailto:takwolf@foxmail.com)

[http://takwolf.com](http://takwolf.com)

## License ##

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
