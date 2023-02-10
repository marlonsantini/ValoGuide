package fingerfire.com.valorant.data.model

import com.squareup.moshi.Json

/** Classe reponsável pelo gerenciamento do campos de recebimento do json
 * sendo usado DataClass e o converter Moshi */
data class AgentDataResponse(
    @field:Json(name = "uuid")
    val idAgent: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "bustPortrait")
    val bustPortrait: String,
    @Json(name = "background")
    val background: String
)