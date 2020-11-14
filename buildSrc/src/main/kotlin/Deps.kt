object Deps {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.0"

    object Kotlin {
        private const val version = "1.4.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object Coroutines {
        private const val version = "1.4.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.3.0"
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    }

    object Room {
        private const val version = "2.2.5"
        const val ktx = "androidx.room:room-ktx:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val testing = "androidx.room:room-testing:$version"
    }

    object Hilt {
        private const val version = "2.28-alpha"
        private const val viewModelVersion = "1.0.0-alpha02"

        const val androidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$viewModelVersion"
        const val viewModelCompiler = "androidx.hilt:hilt-compiler:$viewModelVersion"
        const val androidTesting = "com.google.dagger:hilt-android-testing:$version"
    }

    object Navigation {
        private const val version = "2.3.1"
        const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
    }

    const val junit = "junit:junit:4.12"
    const val assertj = "org.assertj:assertj-core:3.18.0"
    const val mockk = "io.mockk:mockk:1.10.2"

    const val androidxJunitKtx = "androidx.test.ext:junit-ktx:1.1.1"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
    const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
    const val testCoreKtx = "androidx.test:core-ktx:1.3.0"
}