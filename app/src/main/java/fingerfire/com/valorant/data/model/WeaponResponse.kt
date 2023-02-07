package fingerfire.com.valorant.data.model

import com.squareup.moshi.Json

data class WeaponResponse(
    @field:Json(name = "data")
    val weapons: List<WeaponDataResponse>,
    @Json(name = "status")
    val status: Int
)