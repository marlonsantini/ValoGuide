package fingerfire.com.valorant.features.maps.data.repository

import fingerfire.com.valorant.api.ValorantApi
import fingerfire.com.valorant.features.maps.data.response.MapDetailResponse
import fingerfire.com.valorant.features.maps.data.response.MapResponse
import fingerfire.com.valorant.util.Util

class MapsRepository(private val valorantApi: ValorantApi) {

    suspend fun loadMaps(): MapResponse {
        return valorantApi.getMaps(Util.setAppLanguage)
    }

    suspend fun getMapDetail(mapUuid: String): MapDetailResponse {
        return valorantApi.getMapsUuid(mapUuid, Util.setAppLanguage)
    }
}