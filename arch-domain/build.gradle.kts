plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    testImplementation(Deps.junit)
    testImplementation(Deps.assertj)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}
