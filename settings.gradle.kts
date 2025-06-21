pluginManagement {
    repositories {
        maven(uri("https://maven.aliyun.com/repository/central"))
        maven(uri("https://maven.aliyun.com/repository/public"))
        maven(uri("https://maven.aliyun.com/repository/gradle-plugin"))
        maven(uri("https://maven.aliyun.com/repository/google"))
        maven(uri("https://jitpack.io"))
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

    // 明确声明插件版本（关键！）
//    plugins {
//        id("com.google.devtools.ksp") version "2.1.20-1.0.32"
//        // 其他插件...
//    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(uri("https://maven.aliyun.com/repository/central"))
        maven(uri("https://maven.aliyun.com/repository/public"))
        maven(uri("https://maven.aliyun.com/repository/gradle-plugin"))
        maven(uri("https://maven.aliyun.com/repository/google"))
        maven(uri("https://jitpack.io"))
        google()
        mavenCentral()
    }
}

rootProject.name = "GiteeKotlin"
include(":app")
include(":library")
