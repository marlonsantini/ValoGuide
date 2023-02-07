package fingerfire.com.valorant

import android.app.Application
import fingerfire.com.valorant.di.ApiModules
import fingerfire.com.valorant.di.DataModules
import fingerfire.com.valorant.di.NetworkModules
import fingerfire.com.valorant.di.ViewModules
import org.koin.core.context.startKoin

class ValorantApplication : Application() {
    /** Classe de aplicação responsável por dar o star na injeção de dependencia com o Koin
     * uso da lista de modulos iniciada com sucesso
     * já declarada no android manifest com android:name */
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    NetworkModules().getNetworkModules(),
                    ApiModules().getApiModules(),
                    DataModules().getDataModules(),
                    ViewModules().getViewModules()
                )
            )
        }
    }
}