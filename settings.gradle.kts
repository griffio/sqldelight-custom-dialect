pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "sqldelight-custom-dialect"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            val vSqlDelight = "2.0.1"
            plugin("kotlin", "org.jetbrains.kotlin.jvm").version("1.9.20")
            plugin("sqldelight", "app.cash.sqldelight").version(vSqlDelight)
            library("sqldelight-jdbc-driver", "app.cash.sqldelight:jdbc-driver:$vSqlDelight")
            library("sqldelight-postgresql-dialect", "app.cash.sqldelight:postgresql-dialect:$vSqlDelight")
            library("sqldelight-mysql-dialect", "app.cash.sqldelight:mysql-dialect:$vSqlDelight")
            library("sqldelight-compiler-env", "app.cash.sqldelight:compiler-env:$vSqlDelight")
            library("postgresql-jdbc-driver", "org.postgresql:postgresql:42.5.4")
            library("mysql-jdbc-driver", "mysql:mysql-connector-java:8.0.15")
        }
    }
}
include("customDialect")
