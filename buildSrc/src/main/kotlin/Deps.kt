object Deps {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.0.1"

    object Kotlin {
        private const val version = "1.3.72"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.3.0"
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    }

    const val junit = "junit:junit:4.12"
    const val androidxJunit = "androidx.test.ext:junit:1.1.1"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
}