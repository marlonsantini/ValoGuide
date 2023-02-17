package fingerfire.com.valorant.data.model

import com.squareup.moshi.Json

data class MapDetailResponse(
    @Json(name = "data")
    val data: MapDataResponse,
    @Json(name = "status")
    val status: String

)