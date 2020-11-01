plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Constants.compileSdkVersion)

    defaultConfig {
        applicationId = "com.github.galcyurio.todo"
        minSdkVersion(Constants.minSdkVersion)
        targetSdkVersion(Constants.targetSdkVersion)
        versionCode = Constants.versionCode
        versionName = Constants.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.constraintLayout)
    implementation(project(":arch-data"))

    testImplementation(Deps.junit)
    testImplementation(Deps.assertj)
    testImplementation(Deps.mockk)
    androidTestImplementation(Deps.androidxJunit)
    androidTestImplementation(Deps.espressoCore)
}