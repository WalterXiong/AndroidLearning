/**
 * 引用插件
 */
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    // 命名空间
    namespace = "com.androidlearning.helloworld"
    // 项目编译版本
    compileSdk = 35

    // 对项目的更多细节进行配置
    defaultConfig {
        // 每一个应用的唯一标识符，绝对不能重复
        applicationId = "com.androidlearning.helloworld"
        // 指定项目最低兼容的Android系统版本
        minSdk = 28
        // 指定的值表示你在该目标版本上已经做过了充分的测试，系统将会为你的应用程序启用一些最新的功能和特性
        targetSdk = 35
        // 项目版本号
        versionCode = 1
        // 项目的版本名
        versionName = "1.0"

        // 用于在当前项目中启用JUnit测试，你可以为当前项目编写测试用例，以保证功能的正确性和稳定性
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    /**
     *  用于指定生成安装文件的相关配置
     *  通常只会有两个子闭包：一个是 debug，一个是 release。
     *      - debug 闭包用于指定生成测试版安装文件的配置，
     *      - release 闭包用于指定生成正式版安装文件的配置。
     *  另外，debug闭包是可以忽略不写的，因此我们看到上面的代码中就只有一个 release闭包。
     */
    buildTypes {
        release {
            // 用于指定是否对项目的代码进行混淆，true表示混淆，false表示不混淆。
            isMinifyEnabled = false
            /*
             * proguardFiles用于指定混淆时使用的规则文件，这里指定了两个文件：
             * 第一个 proguard-android-optimize.txt是在 <Android SDK>/tools/proguard目录下的，里面是所有项目通用的混淆规则；
             * 第二个 proguard-rules.pro是在当前项目的根目录下的，里面可以编写当前项目特有的混淆规则。
             */
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
    buildToolsVersion = "35.0.0"
}

/**
 * 指定当前项目所有的依赖关系
 */
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}