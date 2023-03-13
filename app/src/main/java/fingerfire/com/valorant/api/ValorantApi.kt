package fingerfire.com.valorant.api

import fingerfire.com.valorant.features.agents.data.response.AgentDetailResponse
import fingerfire.com.valorant.features.agents.data.response.AgentResponse
import fingerfire.com.valorant.features.maps.data.response.MapDetailResponse
import fingerfire.com.valorant.features.maps.data.response.MapResponse
import fingerfire.com.valorant.features.weapons.data.response.WeaponDetailResponse
import fingerfire.com.valorant.features.weapons.data.response.WeaponResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ValorantApi {
    @GET("agents")
    suspend fun getAgents(
        @Query("language") language: String,
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean
    ): Response<AgentResponse>

    @GET("agents/{agentUuid}")
    suspend fun getAgentsUuid(
        @Path("agentUuid") agentUuid: String,
        @Query("language") language: String
    ): AgentDetailResponse

    @GET("weapons")
    suspend fun getWeapons(
        @Query("language") language: String
    ): Response<WeaponResponse>

    @GET("weapons/{weaponUuid}")
    suspend fun getWeaponsUuid(
        @Path("weaponUuid") weaponUuid: String,
        @Query("language") language: String
    ): WeaponDetailResponse

    @GET("maps")
    suspend fun getMaps(
        @Query("language") language: String
    ): Response<MapResponse>

    @GET("maps/{mapUuid}")
    suspend fun getMapsUuid(
        @Path("mapUuid") mapUuid: String,
        @Query("language") language: String
    ): MapDetailResponse

}