package fingerfire.com.valorant.data.model

import com.squareup.moshi.Json

/** Classe reponsável pelo gerenciamento do campos de recebimento do json
 * sendo usado DataClass e o converter Moshi */
data class AgentResponse(
    @field:Json(name = "data")
    val agents: List<AgentDataResponse>,
    @Json(name = "status")
    val status: Int
)