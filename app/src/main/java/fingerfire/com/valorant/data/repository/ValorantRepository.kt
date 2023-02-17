package fingerfire.com.valorant.data.repository

import fingerfire.com.valorant.api.ValorantApi
import fingerfire.com.valorant.data.response.*

class ValorantRepository(private val valorantApi: ValorantApi) {

    suspend fun loadAgents(): AgentResponse {
        return valorantApi.getAgents("pt-BR", true)
    }

    suspend fun getAgentDetail(agentUuid: String): AgentDetailResponse {
        return valorantApi.getAgentsUuid(agentUuid,"pt-BR")
    }

    suspend fun loadWeapons(): WeaponResponse {
        return valorantApi.getWeapons("pt-BR")
    }

    suspend fun getWeaponDetail(weaponUuid: String): WeaponDetailResponse {
        return valorantApi.getWeaponsUuid(weaponUuid,"pt-BR")
    }

    suspend fun loadMaps(): MapResponse {
        return valorantApi.getMaps("pt-BR")
    }

    suspend fun getMapDetail(mapUuid: String): MapDetailResponse {
        return valorantApi.getMapsUuid(mapUuid,"pt-BR")
    }

}