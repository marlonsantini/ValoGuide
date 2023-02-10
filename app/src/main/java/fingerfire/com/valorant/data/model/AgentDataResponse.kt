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
    @Json(name = "fullPortrait")
    val fullPortrait: String,
    @Json(name = "background")
    val background: String,
    @Json(name = "displayIcon")
    val displayIcon: String,
    @Json(name = "abilities")
    val abilities: List<AbilityResponse>,
    @Json(name = "role")
    val role: RoleResponse,
    @Json(name = "voiceLine")
    val voiceLineResponse: VoiceLineResponse
)