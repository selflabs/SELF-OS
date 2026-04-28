package ai.selflabs.selfshell

import ai.selflabs.selfshell.examples.agents.hello.HelloAgent
import ai.selflabs.selfshell.examples.agents.notes.LocalNotesAgent
import ai.selflabs.selfshell.examples.agents.planner.TaskPlannerAgent
import ai.selflabs.selfshell.examples.apps.dashboard.PersonalDashboardApp
import ai.selflabs.selfshell.examples.apps.hello.HelloSelfApp
import kotlinx.coroutines.launch

internal object ShellBootstrap {
    fun start(app: SelfShellApplication) {
        app.applicationScope.launch {
            app.agentRuntime.registerAgent(HelloAgent())
            app.agentRuntime.registerAgent(TaskPlannerAgent())
            app.agentRuntime.registerAgent(LocalNotesAgent())
            app.pluginRegistry.registerApp(HelloSelfApp())
            app.pluginRegistry.registerApp(PersonalDashboardApp())
        }
    }
}
