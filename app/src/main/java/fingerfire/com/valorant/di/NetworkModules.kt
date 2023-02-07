package fingerfire.com.valorant.di

import fingerfire.com.valorant.network.SetupRetrofit
import org.koin.dsl.module

/** Classe de modulo feito para as injeção de dependencia do Retrofit/Network onde no exemlo ela está
 * fazendo instancia de si mesmo, usando o Scope de single
 * nesse caso sendo encerrado no momento em que a aplicação por encerrada por completo */
class NetworkModules {

    fun getNetworkModules() = module {
        single {
            SetupRetrofit.getRetrofit()
        }
    }
}