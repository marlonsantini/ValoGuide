package fingerfire.com.valorant.data.model

import com.squareup.moshi.Json

data class WeaponDataResponse(
    @field:Json(name = "uuid")
    val uuid: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "displayIcon")
    val displayIcon: String
)