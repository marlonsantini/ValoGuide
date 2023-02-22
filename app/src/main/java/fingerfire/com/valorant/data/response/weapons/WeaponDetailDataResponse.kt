package fingerfire.com.valorant.data.response.weapons

import com.squareup.moshi.Json

data class WeaponDetailDataResponse(
    @field:Json(name = "uuid")
    val uuid: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "displayIcon")
    val displayIcon: String,
    @Json(name = "weaponStats")
    val weaponStats: WeaponStatsResponse,
    @Json(name = "shopData")
    val shopData: ShopDataResponse,
    @Json(name = "skins")
    val skins: MutableList<SkinsResponse>
)