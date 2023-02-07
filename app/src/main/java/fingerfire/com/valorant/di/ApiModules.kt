package fingerfire.com.valorant.di

import fingerfire.com.valorant.api.ValorantApi
import org.koin.dsl.module
import retrofit2.Retrofit

/** Classe de modulo feito para as injeção de dependencia da API onde no exemlo ela está
 * fazendo instancia de si mesmo, usando o Scope de factory
 * nesse caso sendo encerrado no momento em que parar de ter uso */
class ApiModules {
    fun getApiModules() = module {
        factory<ValorantApi> {
            get<Retrofit>().create(ValorantApi::class.java)
        }
    }
}