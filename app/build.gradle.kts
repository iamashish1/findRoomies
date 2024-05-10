plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    //DAGGER
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    //DAGGER
}

android {
    namespace = "com.example.findroomies"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
        dataBinding=true
    }

    defaultConfig {
        applicationId = "com.example.findroomies"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}


dependencies {
    //gson json de/serialization
    implementation ("com.google.code.gson:gson:2.10.1")

    //Dagger Hilt(DI)
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-firestore")
    // Coil for image resource

    //viewmodelscope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    //for livedata
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")

    //splash screen
    implementation("androidx.core:core-splashscreen:1.0.0")

    implementation("io.coil-kt:coil:2.6.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
