package fingerfire.com.valorant.features.agents.data.repository

import fingerfire.com.valorant.api.ValorantApi
import fingerfire.com.valorant.features.agents.data.response.AgentDetailResponse
import fingerfire.com.valorant.features.agents.data.response.AgentResponse
import fingerfire.com.valorant.util.Util

class AgentsRepository(private val valorantApi: ValorantApi) {

    suspend fun loadAgents(): AgentResponse {
        return valorantApi.getAgents(Util.setAppLanguage, true)
    }

    suspend fun getAgentDetail(agentUuid: String): AgentDetailResponse {
        return valorantApi.getAgentsUuid(agentUuid, Util.setAppLanguage)
    }
}