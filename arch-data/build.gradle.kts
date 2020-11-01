plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Constants.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Constants.minSdkVersion)
        targetSdkVersion(Constants.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appCompat)
    api(project(":arch-domain"))

    testImplementation(Deps.junit)
    testImplementation(Deps.assertj)
    androidTestImplementation(Deps.androidxJunit)
    androidTestImplementation(Deps.espressoCore)
}
