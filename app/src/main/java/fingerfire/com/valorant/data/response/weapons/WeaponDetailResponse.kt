package fingerfire.com.valorant.data.response.weapons

import com.squareup.moshi.Json

data class WeaponDetailResponse(
    @Json(name = "data")
    val data: WeaponDataResponse,
    @Json(name = "status")
    val status: String
)