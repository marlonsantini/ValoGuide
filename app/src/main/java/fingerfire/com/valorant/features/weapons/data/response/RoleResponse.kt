package fingerfire.com.valorant.features.weapons.data.response

import com.squareup.moshi.Json

data class RoleResponse(
    @Json(name = "description")
    val description: String,
    @Json(name = "displayIcon")
    val displayIcon: String,
    @Json(name = "displayName")
    val displayName: String
)