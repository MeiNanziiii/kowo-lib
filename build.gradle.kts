import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.1.20"
    id("fabric-loom") version "1.10-SNAPSHOT"
}

loom {
    runs {
        create("testClient") {
            client()
            ideConfigGenerated(project.rootProject == project)
            name("Test Client")
            source(sourceSets["test"])
        }
        create("testServer") {
            server()
            ideConfigGenerated(project.rootProject == project)
            name("Test Server")
            source(sourceSets["test"])
        }
    }
}

base.archivesName.set("kowo")

version = libs.versions.owo.lib.get()
group = "xyz.bonfiremc"

repositories {
    maven("https://maven.wispforest.io")
}

dependencies {
    minecraft(libs.minecraft)
    mappings(libs.yarn)

    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.kotlin)
    modImplementation(libs.fabric.api)

    modImplementation(libs.owo.lib)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    withSourcesJar()
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

tasks {
    processResources {
        inputs.property("version", version)

        filesMatching("fabric.mod.json") {
            expand(
                "version" to version,
                "minecraft_version" to libs.versions.minecraft.get()
            )
        }
    }

    jar {
        from("LICENSE")
    }
}
