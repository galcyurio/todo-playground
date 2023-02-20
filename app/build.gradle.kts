@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = Constants.compileSdkVersion

    defaultConfig {
        applicationId = "com.github.galcyurio.todo"
        minSdk = Constants.minSdkVersion
        targetSdk = Constants.targetSdkVersion
        versionCode = Constants.versionCode
        versionName = Constants.versionName

        testInstrumentationRunner = "com.github.galcyurio.todo.HiltTestRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    packagingOptions {
        jniLibs.excludes.add("**/attach_hotspot_windows.dll")
        jniLibs.excludes.add("META-INF/**")
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.Coroutines.android)
    testImplementation(Deps.Coroutines.test)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.constraintLayout)
    implementation(project(":data"))

    implementation(Deps.Hilt.android)
    kapt(Deps.Hilt.androidCompiler)
    androidTestImplementation(Deps.Hilt.androidTesting)
    kaptAndroidTest(Deps.Hilt.androidCompiler)

    implementation(Deps.Navigation.fragmentKtx)
    implementation(Deps.Navigation.uiKtx)
    androidTestImplementation(Deps.Navigation.testing)

    testImplementation(project(":test-shared"))
    androidTestImplementation(project(":test-shared"))
    testImplementation(Deps.junit)
    testImplementation(Deps.truth)
    testImplementation(Deps.mockk)
    androidTestImplementation(Deps.androidxJunitKtx)
    androidTestImplementation(Deps.espressoCore)
    androidTestImplementation(Deps.testCoreKtx)
    testImplementation(Deps.coreTesting)
}
