import SwiftUI

@main
struct SelfOSPIApp: App {
    @StateObject private var environment = AppEnvironment.shared

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(environment)
        }
    }
}
