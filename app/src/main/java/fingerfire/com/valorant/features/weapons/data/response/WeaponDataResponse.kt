package fingerfire.com.valorant.features.weapons.data.response

import com.squareup.moshi.Json

data class WeaponDataResponse(
    @field:Json(name = "uuid")
    val uuid: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "displayIcon")
    val displayIcon: String
)