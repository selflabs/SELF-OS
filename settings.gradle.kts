pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "self-shell"

include(":self-shell")
include(":agent-sdk")
include(":app-sdk")
include(":mock-harmony-mesh")
include(":mock-self-wallet")
include(":mock-app-store")
include(":mock-aura-data")

project(":self-shell").projectDir = file("apps/self-shell")
project(":agent-sdk").projectDir = file("sdk/agent-sdk")
project(":app-sdk").projectDir = file("sdk/app-sdk")
project(":mock-harmony-mesh").projectDir = file("mock/harmony-mesh-client")
project(":mock-self-wallet").projectDir = file("mock/self-wallet")
project(":mock-app-store").projectDir = file("mock/app-store")
project(":mock-aura-data").projectDir = file("mock/aura-data")
