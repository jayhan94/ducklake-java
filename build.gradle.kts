allprojects {
    group = "io.github.jayhan94"
    version = "0.1.0"
    
    repositories {
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/jayhan94/ducklake-java")
            credentials {
                username = System.getenv("USERNAME")?.toString() ?: project.findProperty("gpr.user")?.toString()
                password = System.getenv("TOKEN")?.toString() ?: project.findProperty("gpr.key")?.toString()
            }
        }
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    
    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
                
                pom {
                    name.set(project.name)
                    description.set("DuckLake - A Java library for data lake operations")
                    url.set("https://github.com/jayhan94/ducklake-java")
                    
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    
                    developers {
                        developer {
                            id.set("jayhan94")
                            name.set("Jay Han")
                            email.set("feynmanhan94@gmail.com")
                        }
                    }
                    
                    scm {
                        connection.set("scm:git:git://github.com/jayhan94/ducklake-java.git")
                        developerConnection.set("scm:git:ssh://github.com:jayhan94/ducklake-java.git")
                        url.set("https://github.com/jayhan94/ducklake-java/tree/main")
                    }
                }
            }
        }
        
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/jayhan94/ducklake-java")
                credentials {
                    username = System.getenv("USERNAME")?.toString() ?: project.findProperty("gpr.user")?.toString()
                    password = System.getenv("TOKEN")?.toString() ?: project.findProperty("gpr.key")?.toString()
                }
            }
        }
    }
} 