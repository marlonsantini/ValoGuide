package fingerfire.com.valorant.features.maps.data.response

import com.squareup.moshi.Json

data class MapDataResponse(
    @field:Json(name = "uuid")
    val uuid: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "splash")
    val splash: String,
    @Json(name = "displayIcon")
    val displayIcon: String,
    @Json(name = "coordinates")
    val coordinates: String
)