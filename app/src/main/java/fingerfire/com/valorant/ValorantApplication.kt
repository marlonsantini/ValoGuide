package fingerfire.com.valorant

import android.app.Application
import fingerfire.com.valorant.di.ApiModules
import fingerfire.com.valorant.di.NetworkModules
import fingerfire.com.valorant.features.agents.di.AgentsDataModules
import fingerfire.com.valorant.features.agents.di.AgentsUiModules
import fingerfire.com.valorant.features.maps.di.MapsDataModules
import fingerfire.com.valorant.features.maps.di.MapsUiModules
import fingerfire.com.valorant.features.weapons.di.WeaponsDataModules
import fingerfire.com.valorant.features.weapons.di.WeaponsUiModules
import org.koin.core.context.startKoin

class ValorantApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    NetworkModules().getNetworkModules(),
                    ApiModules().getApiModules(),
                    AgentsDataModules().getAgentsDataModules(),
                    AgentsUiModules().getViewModules(),
                    WeaponsDataModules().getWeaponsDataModules(),
                    WeaponsUiModules().getWeaponsUiModules(),
                    MapsDataModules().getMapsDataModules(),
                    MapsUiModules().getViewModules()
                )
            )
        }
    }
}