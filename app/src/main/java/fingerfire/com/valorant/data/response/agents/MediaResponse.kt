package fingerfire.com.valorant.data.response.agents

import com.squareup.moshi.Json

data class MediaResponse(
    @Json(name = "wave")
    val wave: String
)