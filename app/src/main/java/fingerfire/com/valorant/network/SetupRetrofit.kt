package fingerfire.com.valorant.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/** Classe responsável por fazer o tratamenteo de conexão com o retrofit
 * já com a base url colocada na constante */
object SetupRetrofit {

    private const val BASE_URL = "https://valorant-api.com/v1/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}