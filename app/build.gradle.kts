plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    signingConfigs {
        create("release") {
            storeFile = file("E:\\Project\\KMS\\JKS\\kits.jks")
            storePassword = "kits12345"
            keyPassword = "kits12345"
            keyAlias = "key0"
        }
    }
    namespace = "com.bdpolice.kms"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bdpolice.kms"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        this.apply {
            dataBinding = true
            viewBinding = true
            buildConfig = true
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.media3.exoplayer.hls)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //todo hilt for android dependency injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //todo datastore
    implementation(libs.datastore)

    //todo LoadingButton
    implementation (libs.loadingbutton)

    //todo retrofit http request
    implementation(libs.retrofit)
    implementation(libs.convertergson)
    implementation(libs.okhttp)

    //todo alerter
    implementation(libs.alerter)

    //todo swiperefreshlayout
    implementation(libs.androidx.swiperefreshlayout)

    //todo recyclerview
    implementation (libs.androidx.recyclerview)


    implementation (libs.androidx.media3.exoplayer)
   // implementation ("androidx.media3:media3-exoplayer-dash:1.4.1")
    implementation (libs.androidx.media3.ui)
    //todo facebook shimmer
    implementation (libs.shimmer)

}