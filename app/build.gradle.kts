plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.Coroutines.android)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.constraintLayout)
    implementation(project(":arch-data"))

    implementation(Deps.Hilt.android)
    kapt(Deps.Hilt.androidCompiler)
    implementation(Deps.Hilt.viewModel)

    implementation(Deps.Navigation.fragmentKtx)
    implementation(Deps.Navigation.uiKtx)

    testImplementation(Deps.junit)
    testImplementation(Deps.assertj)
    testImplementation(Deps.mockk)
    androidTestImplementation(Deps.androidxJunit)
    androidTestImplementation(Deps.espressoCore)
}