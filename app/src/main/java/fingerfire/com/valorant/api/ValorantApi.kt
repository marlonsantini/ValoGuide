package fingerfire.com.valorant.api

import fingerfire.com.valorant.data.model.AgentResponse
import retrofit2.http.GET

/** Nessa interface ficam os end-points da API
 * API SEMPRE VAI SER UMA INTERFACE */
interface ValorantApi {
    @GET("agents")
    suspend fun getAgents(
    ): AgentResponse

}