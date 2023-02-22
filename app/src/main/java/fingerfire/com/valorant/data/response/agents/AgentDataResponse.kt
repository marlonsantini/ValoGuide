package fingerfire.com.valorant.data.response.agents

import com.squareup.moshi.Json
import fingerfire.com.valorant.data.response.weapons.RoleResponse

data class AgentDataResponse(
    @field:Json(name = "uuid")
    val uuid: String,
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
    val abilities: List<AbilitiesResponse>,
    @Json(name = "role")
    val role: RoleResponse,
    @Json(name = "voiceLine")
    val voiceLine: VoiceLineResponse
)