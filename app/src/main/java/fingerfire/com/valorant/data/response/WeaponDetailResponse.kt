package fingerfire.com.valorant.data.response

import com.squareup.moshi.Json

data class WeaponDetailResponse(
    @Json(name = "data")
    val data: WeaponDataResponse,
    @Json(name = "status")
    val status: String
)