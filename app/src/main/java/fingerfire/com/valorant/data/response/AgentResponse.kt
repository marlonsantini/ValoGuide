package fingerfire.com.valorant.data.response

import com.squareup.moshi.Json

data class AgentResponse(
    @field:Json(name = "data")
    val agents: List<AgentDataResponse>,
    @Json(name = "status")
    val status: Int
)