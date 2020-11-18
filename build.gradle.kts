// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Deps.androidGradlePlugin)
        classpath(Deps.Kotlin.gradlePlugin)
        classpath(Deps.Hilt.androidGradlePlugin)
        classpath(Deps.Navigation.plugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
