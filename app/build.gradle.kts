plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // plugin de Google services Gradle
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.exoticworld"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.exoticworld"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Navigation Compose
    implementation(libs.androidxNavigation)
    // ViewModel en Compose
    implementation(libs.androidxLifecycleViewmodelCompose)
    // Iconos Material
    implementation(libs.composeMaterialIcons)

    // Import Firebase BoM, cuando se usa el BoM no se necesita especificar versions en las dependencias
    implementation(libs.firebase.bom)
    implementation(libs.firebase.analytics)
    implementation("com.firebaseui:firebase-ui-auth:9.1.1")
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)

    // Integración de Coil para mostrar las imagenes jejej
    implementation("io.coil-kt:coil-compose:2.6.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
