package fingerfire.com.valorant

import android.app.Application
import fingerfire.com.valorant.di.ApiModules
import fingerfire.com.valorant.di.DataModules
import fingerfire.com.valorant.di.NetworkModules
import fingerfire.com.valorant.di.ViewModules
import org.koin.core.context.startKoin

class ValorantApplication : Application() {

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