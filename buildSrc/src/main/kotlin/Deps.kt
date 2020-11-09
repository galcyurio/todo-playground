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

    const val junit = "junit:junit:4.12"
    const val assertj = "org.assertj:assertj-core:3.18.0"
    const val mockk = "io.mockk:mockk:1.10.2"

    const val androidxJunit = "androidx.test.ext:junit:1.1.1"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
}