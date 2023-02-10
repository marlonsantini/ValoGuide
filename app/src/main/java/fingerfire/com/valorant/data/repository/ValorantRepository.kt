package fingerfire.com.valorant.data.repository

import fingerfire.com.valorant.api.ValorantApi
import fingerfire.com.valorant.data.model.AgentResponse
import fingerfire.com.valorant.data.model.MapResponse
import fingerfire.com.valorant.data.model.WeaponResponse

/**
 * Classe de repositorio faz parte da camada de dados
 * Nesse Exmeplo já esta com injeção de de dependencia implementado e coroutines
 * Nessa classe é realizada a gestão de dados */
class ValorantRepository(private val valorantApi: ValorantApi) {

    suspend fun loadAgents(): AgentResponse {
        return valorantApi.getAgents("pt-BR", true)
    }

    suspend fun loadWeapons(): WeaponResponse {
        return valorantApi.getWeapons("pt-BR")
    }

    suspend fun loadMaps(): MapResponse {
        return valorantApi.getMaps("pt-BR")
    }

}