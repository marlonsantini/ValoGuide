package fingerfire.com.valorant.data.response

import com.squareup.moshi.Json

data class MapResponse(
    @field:Json(name = "data")
    val maps: List<MapDataResponse>,
    @Json(name = "status")
    val status: Int
)