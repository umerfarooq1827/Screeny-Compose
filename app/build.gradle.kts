plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.shahid.iqbal.screeny"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.shahid.iqbal.screeny"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions { jvmTarget = "21" }

    buildFeatures {
        compose = true
    }


    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

composeCompiler {
    enableStrongSkippingMode = true
    enableIntrinsicRemember = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //lifecycle-compose
    implementation(libs.androidx.lifecycle.runtime.compose.android)

    //Coil Image Loading Library
    implementation(libs.coil.compose)

    //Koin As Dependency Injection
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    //Ktor for Network Requests
    implementation(libs.bundles.ktor.client)

    //Room Database
    implementation(libs.bundles.room.db)
    ksp(libs.room.ksp)

    //Paging Library
    implementation(libs.pagging.library)

    //Reels Player
    implementation(libs.reels.player)

}