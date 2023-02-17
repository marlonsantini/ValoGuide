package fingerfire.com.valorant.data.response

import com.squareup.moshi.Json

data class WeaponResponse(
    @field:Json(name = "data")
    val weapons: List<WeaponDataResponse>,
    @Json(name = "status")
    val status: Int
)