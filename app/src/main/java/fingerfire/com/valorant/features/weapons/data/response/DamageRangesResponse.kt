package fingerfire.com.valorant.features.weapons.data.response

import com.squareup.moshi.Json

data class DamageRangesResponse(
    @Json(name = "rangeStartMeters")
    val rangeStartMeters: String,
    @Json(name = "rangeEndMeters")
    val rangeEndMeters: String,
    @Json(name = "headDamage")
    val headDamage: Double,
    @Json(name = "bodyDamage")
    val bodyDamage: Double,
    @Json(name = "legDamage")
    val legDamage: Double

)