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
    compileOnly("org.apache.spark:spark-core_${scalaBinVersion}:${sparkVersion}")
    
    // Test dependencies
    testImplementation(libs.junit)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}