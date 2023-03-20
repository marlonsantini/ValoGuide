package fingerfire.com.valorant.features.agents.data.repository

import fingerfire.com.valorant.api.ValorantApi
import fingerfire.com.valorant.extensions.getAppLanguage
import fingerfire.com.valorant.features.agents.data.response.AgentDetailResponse
import fingerfire.com.valorant.features.agents.data.response.AgentResponse
import retrofit2.Response

class AgentsRepository(private val valorantApi: ValorantApi) {

    suspend fun loadAgents(): Response<AgentResponse> {
        return valorantApi.getAgents(getAppLanguage(), true)
    }

    suspend fun getAgentDetail(agentUuid: String): AgentDetailResponse {
        return valorantApi.getAgentsUuid(agentUuid, getAppLanguage())
    }
}