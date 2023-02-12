package fingerfire.com.valorant.di

import fingerfire.com.valorant.api.ValorantApi
import org.koin.dsl.module
import retrofit2.Retrofit

class ApiModules {
    fun getApiModules() = module {
        factory<ValorantApi> {
            get<Retrofit>().create(ValorantApi::class.java)
        }
    }
}