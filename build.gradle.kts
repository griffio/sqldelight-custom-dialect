plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.sqldelight)
    application
}

group = "griffio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.sqldelight.jdbc.driver)
    api(project(":customDialect"))
    implementation(libs.postgresql.jdbc.driver)
    testImplementation(kotlin("test"))
}

sqldelight {
    databases {
        create("Sample") {
            packageName.set("griffio.queries")
            dialect(project(":customDialect"))
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("griffio.MainKt")
}
