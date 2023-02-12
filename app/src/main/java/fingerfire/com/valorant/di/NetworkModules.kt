package fingerfire.com.valorant.di

import fingerfire.com.valorant.network.SetupRetrofit
import org.koin.dsl.module

class NetworkModules {

    fun getNetworkModules() = module {
        single {
            SetupRetrofit.getRetrofit()
        }
    }
}