object Deps {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.4.0"

    object Kotlin {
        private const val version = "1.8.0"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object Coroutines {
        private const val version = "1.6.4"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.9.0"
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.5.5"
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    }

    object Room {
        private const val version = "2.5.0"
        const val ktx = "androidx.room:room-ktx:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val testing = "androidx.room:room-testing:$version"
    }

    const val javaxInject = "javax.inject:javax.inject:1"

    object Hilt {
        private const val version = "2.44"
        const val androidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val androidTesting = "com.google.dagger:hilt-android-testing:$version"
    }

    object Navigation {
        private const val version = "2.5.3"
        const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        const val testing = "androidx.navigation:navigation-testing:$version"
        const val plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
    }

    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val okhttp = "com.squareup.okhttp3:okhttp:4.10.0"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:4.10.0"

    const val junit = "junit:junit:4.12"
    const val truth = "com.google.truth:truth:1.1.3"
    const val mockk = "io.mockk:mockk:1.13.4"

    const val androidxJunitKtx = "androidx.test.ext:junit-ktx:1.1.5"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.5.1"
    const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
    const val testCoreKtx = "androidx.test:core-ktx:1.5.0"
}
