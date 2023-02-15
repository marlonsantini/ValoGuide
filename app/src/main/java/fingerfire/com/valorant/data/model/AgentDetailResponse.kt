package fingerfire.com.valorant.data.model

import com.squareup.moshi.Json

data class AgentDetailResponse(
    @Json(name = "data")
    val data: AgentDataResponse,
    @Json(name = "status")
    val status: String
)