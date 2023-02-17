package fingerfire.com.valorant.data.model

import com.squareup.moshi.Json

data class WeaponDetailResponse(
    @Json(name = "data")
    val data: WeaponDataResponse,
    @Json(name = "status")
    val status: String
)