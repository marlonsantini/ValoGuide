package fingerfire.com.valorant.features.weapons.data.response

import com.squareup.moshi.Json

data class DamageRangesResponse(
    @Json(name = "headDamage")
    val headDamage: Float,
    @Json(name = "bodyDamage")
    val bodyDamage: Float,
    @Json(name = "legDamage")
    val legDamage: Float

)