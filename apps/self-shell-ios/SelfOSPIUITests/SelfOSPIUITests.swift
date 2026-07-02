import XCTest

final class LaunchSmokeTests: XCTestCase {

    func testAppLaunchesHomeScreen() {
        let app = XCUIApplication()
        app.launch()

        XCTAssertTrue(app.descendants(matching: .any)["home_screen"].waitForExistence(timeout: 10))
        XCTAssertTrue(app.descendants(matching: .any)["home_welcome"].exists)
        XCTAssertTrue(app.tabBars.buttons["Home"].exists)
    }
}
