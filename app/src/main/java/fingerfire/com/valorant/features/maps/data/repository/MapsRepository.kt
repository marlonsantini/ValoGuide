package fingerfire.com.valorant.features.maps.data.repository

import fingerfire.com.valorant.api.ValorantApi
import fingerfire.com.valorant.features.maps.data.response.MapDetailResponse
import fingerfire.com.valorant.features.maps.data.response.MapResponse

class MapsRepository(private val valorantApi: ValorantApi) {

    suspend fun loadMaps(): MapResponse {
        return valorantApi.getMaps("pt-BR")
    }

    suspend fun getMapDetail(mapUuid: String): MapDetailResponse {
        return valorantApi.getMapsUuid(mapUuid,"pt-BR")
    }
}