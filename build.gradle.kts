plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.9.24"
  id("org.jetbrains.intellij") version "1.17.3"
  id("antlr")
}

group = "com.zocdoc.platform"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.antlr:antlr4-runtime:4.13.1")
  antlr("org.antlr:antlr4:4.13.1")
}

intellij {
  version.set("2024.1.4")
  type.set("IC")

  plugins.set(listOf("com.intellij.java"))
}

tasks.generateGrammarSource {
  maxHeapSize = "64m"
  arguments = arguments + listOf(
    "-visitor",
    "-long-messages",
    "-package",
    "com.zocdoc.grammar"
  )
}

idea {
  module {
    sourceDirs.plusAssign(file("${layout.buildDirectory}/src/main/antlr"))
    generatedSourceDirs.plusAssign(file("${layout.buildDirectory}/src/main/antlr"))
  }
}

tasks {
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
    options.encoding = "utf-8"
  }

  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
  }

  patchPluginXml {
    sinceBuild.set("232")
    untilBuild.set("242.*")
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}
