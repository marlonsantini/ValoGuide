package fingerfire.com.valorant.data.model

import com.squareup.moshi.Json

data class WeaponDataResponse(
    @Json(name = "displayName")
    val displayName: String
)