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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packagingOptions {
        exclude("META-INF/*")
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appCompat)
    api(Deps.AndroidX.lifecycleKtx)
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
