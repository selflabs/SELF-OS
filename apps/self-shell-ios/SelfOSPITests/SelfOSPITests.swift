import XCTest
@testable import SelfOSPI

final class AgentRuntimeTests: XCTestCase {

    @MainActor
    func testRegisterAndSendRequest() async {
        let runtime = AgentRuntime()
        await runtime.register(HelloAgent())

        XCTAssertEqual(runtime.manifests.count, 1)

        let response = await runtime.sendRequest(agentId: "hello-agent", input: "ping")
        XCTAssertTrue(response.ok)
        XCTAssertTrue(response.output.contains("ping"))
    }

    @MainActor
    func testUnknownAgentReturnsError() async {
        let runtime = AgentRuntime()
        let response = await runtime.sendRequest(agentId: "missing", input: "hi")
        XCTAssertFalse(response.ok)
    }
}

final class ExampleAgentTests: XCTestCase {

    func testHelloAgentIncludesInput() async {
        let agent = HelloAgent()
        let request = AgentRequest(id: "r1", input: "testing", createdAt: Date())
        let response = await agent.handleRequest(request)
        XCTAssertTrue(response.ok)
        XCTAssertTrue(response.output.contains("testing"))
    }

    func testLocalNotesAddAndList() async {
        let agent = LocalNotesAgent()
        let add = await agent.handleRequest(AgentRequest(id: "r1", input: "add Buy milk", createdAt: Date()))
        XCTAssertTrue(add.ok)
        let list = await agent.handleRequest(AgentRequest(id: "r2", input: "list", createdAt: Date()))
        XCTAssertTrue(list.output.contains("Buy milk"))
        await agent.shutdown()
    }
}

final class MockClientTests: XCTestCase {

    @MainActor
    func testHarmonyMeshTaskCount() async {
        let mesh = MockHarmonyMeshClient()
        XCTAssertEqual(mesh.listTasks().count, 3)
    }

    @MainActor
    func testAppStoreInstall() async {
        let store = MockAppStoreClient()
        let app = CatalogApp(id: "t1", name: "Test", description: "D", category: "Tools")
        store.install(app)
        XCTAssertEqual(store.installed.count, 1)
    }
}
