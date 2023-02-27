package fingerfire.com.valorant.features.weapons.data.response

import com.squareup.moshi.Json

data class SkinsResponse(
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "displayIcon")
    val displayIcon: String?,
    @Json(name = "contentTierUuid")
    val contentTierUuid: String?
)