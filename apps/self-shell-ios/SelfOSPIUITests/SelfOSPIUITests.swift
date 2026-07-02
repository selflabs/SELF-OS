import XCTest

final class LaunchSmokeTests: XCTestCase {

    func testAppLaunchesHomeScreen() {
        let app = XCUIApplication()
        app.launch()

        let headline = app.staticTexts["SELF OS Personal Intelligence (Community Edition)"]
        XCTAssertTrue(headline.waitForExistence(timeout: 10))

        XCTAssertTrue(app.tabBars.buttons["Home"].exists)
    }
}
