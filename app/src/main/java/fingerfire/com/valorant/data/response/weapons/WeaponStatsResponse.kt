package fingerfire.com.valorant.data.response.weapons

import com.squareup.moshi.Json

data class WeaponStatsResponse(
    @Json(name = "damageRanges")
    val damageRanges: List<DamageRangesResponse>
)