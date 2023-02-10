package fingerfire.com.valorant.api

import fingerfire.com.valorant.data.model.AgentResponse
import fingerfire.com.valorant.data.model.MapResponse
import fingerfire.com.valorant.data.model.WeaponResponse
import retrofit2.http.GET
import retrofit2.http.Query

/** Nessa interface ficam os end-points da API
 * API SEMPRE VAI SER UMA INTERFACE */
interface ValorantApi {
    @GET("agents")
    suspend fun getAgents(
        @Query("language") language: String,
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean
    ): AgentResponse

    @GET("weapons")
    suspend fun getWeapons(
        @Query("language") language: String
    ): WeaponResponse

    @GET("maps")
    suspend fun getMaps(
        @Query("language") language: String
    ): MapResponse

}