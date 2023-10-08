<<<<<<< HEAD
=======
import org.apache.tools.ant.util.JavaEnvUtils.VERSION_1_8

>>>>>>> d2b3ea0 (Initial commit)
plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.njrlib"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.njrlib"
        minSdk = 29
        targetSdk = 33
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
    buildFeatures {
        viewBinding=true
    }
    compileOptions {
<<<<<<< HEAD
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
=======

        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "30.0.3"
>>>>>>> d2b3ea0 (Initial commit)
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
<<<<<<< HEAD
    implementation("com.google.firebase:firebase-auth:22.1.2")
    implementation("com.google.firebase:firebase-firestore:24.8.1")

    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.google.firebase:firebase-storage:20.2.1")
    implementation("com.google.firebase:firebase-database:20.2.2")
=======
    implementation("com.google.firebase:firebase-firestore:24.8.1")

    implementation("de.hdodenhof:circleimageview:3.1.0")

>>>>>>> d2b3ea0 (Initial commit)

    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.squareup.picasso:picasso:2.71828")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

<<<<<<< HEAD

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
=======
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Import the BoM for the Firebase platform
    //implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-storage:20.2.1")
    implementation("com.google.firebase:firebase-database:20.2.2")
    implementation("com.google.firebase:firebase-auth:22.1.2")
    implementation("com.google.android.gms:play-services-auth:20.7.0")

>>>>>>> d2b3ea0 (Initial commit)
}