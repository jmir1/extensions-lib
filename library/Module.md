# Module extensions-lib
The Aniyomi API exposed to extensions via stubs.

# Package androidx.preference
Android preferences classes exposed to extensions.

## NOTE
Not all classes from [*androidx.preference*](https://developer.android.com/reference/androidx/preference/package-summary) package
were implemented as stubs, so if you need to use a non-implemented class, you must add the following into the extension's build.gradle file:
```kotlin
dependencies {
    compileOnly("androidx.preference:preference-ktx:1.2.0")
}
```

# Package eu.kanade.tachiyomi.animesource
Classes and interfaces for more complex extensions, like ones with multiple sources or user preferences.

# Package eu.kanade.tachiyomi.animesource.model
Required data classes to interact with Aniyomi.

# Package eu.kanade.tachiyomi.animesource.online
Single-source creation classes, sufficient to most extensions.

# Package eu.kanade.tachiyomi.network
Useful methods for creating http requests and manipulate responses.

# Package eu.kanade.tachiyomi.network.interceptor
Useful methods to slow down http requests and prevent IP-ban or accidental DDoS.

