package fingerfire.com.valorant.features.agents.ui.viewstate

import fingerfire.com.valorant.features.agents.data.response.AgentResponse

data class AgentViewState(val sucess: AgentResponse? = null, val failure: Boolean = false)