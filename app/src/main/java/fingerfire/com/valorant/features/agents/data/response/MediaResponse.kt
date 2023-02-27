package fingerfire.com.valorant.features.agents.data.response

import com.squareup.moshi.Json

data class MediaResponse(
    @Json(name = "wave")
    val wave: String
)