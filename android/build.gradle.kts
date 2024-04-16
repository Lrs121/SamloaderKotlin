plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.moko.resources)
}

group = rootProject.extra["groupName"].toString()
version = rootProject.extra["versionName"].toString()

dependencies {
    implementation(project(":common"))
}

android {
    val compileSdk: Int by rootProject.extra
    val packageName: String by rootProject.extra

    this.compileSdk = compileSdk

    defaultConfig {
        applicationId = packageName

        val minSdk: Int by rootProject.extra
        val targetSdk: Int by rootProject.extra
        val versionCode: Int by rootProject.extra
        val versionName: String by rootProject.extra

        this.minSdk = minSdk
        this.targetSdk = targetSdk

        this.versionCode = versionCode
        this.versionName = versionName

        setProperty("archivesBaseName", "bifrost_android_$versionName")
    }

    namespace = packageName

    buildFeatures {
        compose = true
        aidl = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    compileOptions {
        val javaVersionEnum: JavaVersion by rootProject.extra
        sourceCompatibility = javaVersionEnum
        targetCompatibility = javaVersionEnum
    }

    kotlinOptions {
        jvmTarget = rootProject.extra["javaVersionEnum"].toString()
    }

    lint {
        abortOnError = false
    }

    packaging {
        resources.excludes.add("META-INF/versions/9/previous-compilation-data.bin")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    androidResources {
        generateLocaleConfig = true
    }
}

multiplatformResources {
    resourcesPackage.set("tk.zwander.samloaderkotlin.android")
}

compose {
    kotlinCompilerPlugin.set("org.jetbrains.compose.compiler:compiler:${libs.versions.compose.compiler.get()}")
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=${libs.versions.kotlin.get()}")
}
