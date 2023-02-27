package fingerfire.com.valorant.features.weapons.data.response

import com.squareup.moshi.Json

data class WeaponDetailResponse(
    @Json(name = "data")
    val data: WeaponDetailDataResponse,
    @Json(name = "status")
    val status: String
)