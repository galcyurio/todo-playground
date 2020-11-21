plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(Constants.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Constants.minSdkVersion)
        targetSdkVersion(Constants.targetSdkVersion)
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.AndroidX.appCompat)

    implementation(Deps.junit)
    api(Deps.Coroutines.test)
}