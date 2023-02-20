@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Constants.compileSdkVersion

    defaultConfig {
        minSdk = Constants.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packagingOptions {
        jniLibs.excludes.add("META-INF/*")
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appCompat)
    api(Deps.AndroidX.lifecycleKtx)
    implementation(Deps.javaxInject)
    api(Deps.retrofit)
    api(Deps.retrofitGson)
    api(Deps.okhttp)
    implementation("com.squareup.retrofit2:converter-gson")

    api(Deps.Room.ktx)
    kapt(Deps.Room.compiler)
    testImplementation(Deps.Room.testing)

    testImplementation(project(":test-shared"))
    androidTestImplementation(project(":test-shared"))
    testImplementation(Deps.mockWebServer)
    testImplementation(Deps.junit)
    testImplementation(Deps.truth)
    testImplementation(Deps.mockk)
    androidTestImplementation(Deps.androidxJunitKtx)
    androidTestImplementation(Deps.espressoCore)
    androidTestImplementation(Deps.coreTesting)
}
