plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = Constants.compileSdkVersion

    defaultConfig {
        minSdk = Constants.minSdkVersion
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.AndroidX.appCompat)

    implementation(Deps.junit)
    api(Deps.Coroutines.test)
}
