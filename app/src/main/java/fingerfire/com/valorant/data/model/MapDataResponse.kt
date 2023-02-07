package fingerfire.com.valorant.data.model

import com.squareup.moshi.Json

data class MapDataResponse(
    @Json(name = "displayName")
    val displayName: String
)