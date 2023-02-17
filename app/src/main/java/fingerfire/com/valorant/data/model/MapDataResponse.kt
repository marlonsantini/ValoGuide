package fingerfire.com.valorant.data.model

import com.squareup.moshi.Json

data class MapDataResponse(
    @field:Json(name = "uuid")
    val uuid: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "splash")
    val splash: String,
    @Json(name = "displayicon")
    val displayicon: String
)