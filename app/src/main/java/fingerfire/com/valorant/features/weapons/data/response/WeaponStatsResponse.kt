package fingerfire.com.valorant.features.weapons.data.response

import com.squareup.moshi.Json

data class WeaponStatsResponse(
    @Json(name = "damageRanges")
    val damageRanges: MutableList<DamageRangesResponse>
)