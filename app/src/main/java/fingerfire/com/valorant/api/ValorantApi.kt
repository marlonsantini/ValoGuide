package fingerfire.com.valorant.api

import fingerfire.com.valorant.data.model.*
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

    @GET("weapons/{weaponUuid}")
    suspend fun getWeaponsUuid(
        @Path("weaponUuid") weaponUuid: String,
        @Query("language") language: String
    ): WeaponDetailResponse

    @GET("maps")
    suspend fun getMaps(
        @Query("language") language: String
    ): MapResponse

    @GET("maps/{mapUuid}")
    suspend fun getMapsUuid(
        @Path("mapUuid") mapUuid: String,
        @Query("language") language: String
    ): MapDetailResponse

}