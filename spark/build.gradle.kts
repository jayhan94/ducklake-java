plugins {
    `java-library`
    `scala`
}

repositories {
    mavenCentral()
}

val scalaBinVersion = "2.13"
val sparkVersion = "4.0.0"

dependencies {
    // Add a dependency on the core DuckLake module
    implementation(project(":core"))

    // Scala library
    compileOnly("org.scala-lang:scala-library")

    // Spark dependencies should be provided by the execution environment
    compileOnly("org.apache.spark:spark-sql_${scalaBinVersion}:${sparkVersion}")

    // JUnit Jupiter (JUnit 5) test dependencies
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation("org.apache.spark:spark-sql_${scalaBinVersion}:${sparkVersion}")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.test {
    useJUnitPlatform()
}