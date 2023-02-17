package fingerfire.com.valorant.data.response

import com.squareup.moshi.Json

data class WeaponDataResponse(
    @field:Json(name = "uuid")
    val uuid: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "displayIcon")
    val displayIcon: String,
    @Json(name = "shopData")
    val shopData: String,
)