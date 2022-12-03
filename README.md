# Huda_Application

# Project Description

The HUDA mobile application is a Java based Android application that assists patients that use the HUDA clinic in a variety of ways that 
streamline the many tedious processes patients typically have to go through such as:
  - Registering for Athena
  - Creating/Requesting appointments from the patient side
  - Accepting/Denying appointments from the clinic side
  - Checking into appointments
  - Canceling Appointment

Some of the technologies that we used in the application are:
  - Android Studio
  - Firebase Authentication API
  - Firebase Realtime database
  - Gradle
  
Some of the challenges that we faced during the development process were:
  - Not having access to the Athena API
  - Limited time schedule
  - Time conflicts with work and other courses
 
# How to install and Run the project
  1. Install the lastest version of Android Studio from the link here 
  https://developer.android.com/studio/?gclid=Cj0KCQiA4aacBhCUARIsAI55maH7Qoc-CGsc5NIX6rMX5BIz3BTjd__GbZ05oEkFNoh4Ws4K4PUIJQAaAtJXEALw_wcB&gclsrc=aw.ds
  2. Clone the project via the GitHub reponsitory 
  3. Firebase and Google Dependencies/Plug-ins will come built into the project under Gradle (Check below for reference)
  
  build.gradle(Huda_Application)
  
  buildscript {
    repositories {
        // Make sure that you have the following two repositories
        google()  // Google's Maven repository
        mavenCentral()  // Maven Central repository

    }
    dependencies {
        classpath 'com.google.gms:google-services:4.3.14'
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id 'com.android.application' version '7.2.2' apply false
    id 'com.android.library' version '7.2.2' apply false
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

build.gradle (app)

plugins {
    id 'com.android.application'
    id 'com.google.gms.google-services'
}

android {
    compileSdk 32

    defaultConfig {
        applicationId "com.example.huda_application"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"
        multiDexEnabled = true

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_11
        targetCompatibility JavaVersion.VERSION_11

        // Flag to enable support for the new language APIs
        coreLibraryDesugaringEnabled = true
        // Sets Java compatibility to Java 8
        sourceCompatibility = JavaVersion.VERSION_1_9
        targetCompatibility = JavaVersion.VERSION_1_9
    }
    packagingOptions{
        exclude 'META-INF/NOTICE.md'
        exclude 'META-INF/LICENSE.md'
    }
    buildFeatures {
        viewBinding true
    }
}

dependencies {

    implementation 'androidx.appcompat:appcompat:1.5.1'
    implementation 'com.google.android.material:material:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.navigation:navigation-fragment:2.5.2'
    implementation 'androidx.navigation:navigation-ui:2.5.2'
    implementation 'com.google.firebase:firebase-auth:21.0.8'
    implementation 'com.google.firebase:firebase-database:20.0.6'
    implementation 'com.google.android.gms:play-services-tasks:18.0.2'
    implementation 'com.google.firebase:firebase-firestore:24.3.1'


    implementation 'com.sun.mail:android-mail:1.6.6'
    implementation 'com.sun.mail:android-activation:1.6.7'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    implementation 'androidx.test:monitor:1.4.0'
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.9.1'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    implementation platform('com.google.firebase:firebase-bom:30.4.1')
    implementation 'com.wdullaer:materialdatetimepicker:4.2.3'

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}

  4. JDK version: 11.0
  5. Gradle plugin version: 7.2.2
  6. Gradle version: 7.3.3
  7. Connect Project to Firebase:
    1. You will need to be given access to use Firebase and makes changes
    2. Clicks tools on top of the IDE
    3. Scroll down to Firebase and click
    4. Click authentication and "Authenticate using a custom authentication system" and follow the steps provided by Firebase
    5. After Authentication is set up, go back to tools and click Firebase
    6. Select Realtime Database and click "Get started with Realtime database" and follow the steps provided by Firebase

# How to use the Project
  1. Set up an emulator of the device Pixel 4 XL API 30
  2. Click "run" to let the emulator run and display the application 
  
# Accounts for testing
  1. Patient Account:
    -Email Address: testhudapatient@gmail.com
    -Password: Password123%
  2. Admin Account:
    -Email Address: 
    -Password:
