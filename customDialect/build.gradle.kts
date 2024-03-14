plugins {
    alias(libs.plugins.kotlin)
}

group = "griffio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    api(libs.sqldelight.mysql.dialect)
    compileOnly(libs.sqldelight.compiler.env)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

