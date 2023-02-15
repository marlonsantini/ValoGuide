package fingerfire.com.valorant.api

import fingerfire.com.valorant.data.model.AgentDetailResponse
import fingerfire.com.valorant.data.model.AgentResponse
import fingerfire.com.valorant.data.model.MapResponse
import fingerfire.com.valorant.data.model.WeaponResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ValorantApi {
    @GET("agents")
    suspend fun getAgents(
        @Query("language") language: String,
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean
    ): AgentResponse

    @GET("agents/{agentUuid}")
    suspend fun getAgentsUuid(
        @Path("agentUuid") agentUuid: String,
        @Query("language") language: String
    ): AgentDetailResponse

    @GET("weapons")
    suspend fun getWeapons(
        @Query("language") language: String
    ): WeaponResponse

    @GET("maps")
    suspend fun getMaps(
        @Query("language") language: String
    ): MapResponse

}