plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    
    // Spark dependencies
    implementation("org.apache.spark:spark-sql_2.12:3.5.0")
    implementation("org.apache.spark:spark-core_2.12:3.5.0")
    
    // Use SLF4J for logging abstraction
    implementation("org.slf4j:slf4j-api:2.0.9")
    
    // Test dependencies
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.test {
    useJUnitPlatform()
} 