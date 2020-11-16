plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
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
    implementation(Deps.javaxInject)

    api(Deps.Room.ktx)
    kapt(Deps.Room.compiler)
    testImplementation(Deps.Room.testing)

    testImplementation(project(":test-shared"))
    androidTestImplementation(project(":test-shared"))
    testImplementation(Deps.junit)
    testImplementation(Deps.assertj)
    testImplementation(Deps.mockk)
    androidTestImplementation(Deps.androidxJunitKtx)
    androidTestImplementation(Deps.espressoCore)
    androidTestImplementation(Deps.coreTesting)
}
