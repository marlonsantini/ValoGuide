package fingerfire.com.valoguide.features.agents.ui.viewstate

import fingerfire.com.valoguide.features.agents.data.response.AgentDetailResponse

data class AgentDetailViewState(
    val sucess: AgentDetailResponse? = null,
    val failure: Boolean = false
)
