package fingerfire.com.valorant.data.response.agents

import com.squareup.moshi.Json

data class VoiceLineResponse(
    @Json(name = "mediaList")
    val mediaList: List<MediaResponse>
)