plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.carplaydrive.app"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.carplaydrive.app"
        minSdk = 23
        targetSdk = 35
        versionCode = 2
        versionName = "2.0"
    }
}
