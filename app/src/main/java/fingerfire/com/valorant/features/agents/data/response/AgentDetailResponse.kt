package fingerfire.com.valorant.features.agents.data.response

import com.squareup.moshi.Json

data class AgentDetailResponse(
    @Json(name = "data")
    val data: AgentDataResponse,
    @Json(name = "status")
    val status: String
)