package fingerfire.com.valorant.features.maps.data.repository

import fingerfire.com.valorant.api.ValorantApi
import fingerfire.com.valorant.extensions.getAppLanguage
import fingerfire.com.valorant.features.maps.data.response.MapDetailResponse
import fingerfire.com.valorant.features.maps.data.response.MapResponse
import retrofit2.Response

class MapsRepository(private val valorantApi: ValorantApi) {

    suspend fun loadMaps(): Response<MapResponse> {
        return valorantApi.getMaps(getAppLanguage())
    }

    suspend fun getMapDetail(mapUuid: String): MapDetailResponse {
        return valorantApi.getMapsUuid(mapUuid, getAppLanguage())
    }
}