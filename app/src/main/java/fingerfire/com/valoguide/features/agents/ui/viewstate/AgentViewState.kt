package fingerfire.com.valoguide.features.agents.ui.viewstate

import fingerfire.com.valoguide.features.agents.data.response.AgentDataResponse
import fingerfire.com.valoguide.features.agents.data.response.AgentResponse

data class AgentViewState(
    val sucess: AgentResponse? = null,
    val failure: Boolean = false,
    val selectedRole: String? = null
) {
    val filteredAgents: List<AgentDataResponse>
        get() {
            val agents = sucess?.agents.orEmpty()
            val role = selectedRole
            return if (role.isNullOrBlank()) {
                agents
            } else {
                agents.filter { it.role.displayName.contains(role, ignoreCase = true) }
            }
        }
}
