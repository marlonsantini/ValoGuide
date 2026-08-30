package fingerfire.com.valoguide

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import fingerfire.com.valoguide.di.ApiModules
import fingerfire.com.valoguide.di.ComposeModules
import fingerfire.com.valoguide.di.NetworkModules
import fingerfire.com.valoguide.features.agents.di.AgentsDataModules
import fingerfire.com.valoguide.features.agents.di.AgentsUiModules
import fingerfire.com.valoguide.features.maps.di.MapsDataModules
import fingerfire.com.valoguide.features.maps.di.MapsUiModules
import fingerfire.com.valoguide.features.weapons.di.WeaponsDataModules
import fingerfire.com.valoguide.features.weapons.di.WeaponsUiModules
import org.koin.core.context.startKoin

class ValorantApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this)
        FirebaseApp.initializeApp(this)

        startKoin {
            modules(
                listOf(
                    NetworkModules().getNetworkModules(),
                    ApiModules().getApiModules(),
                    ComposeModules().getComposeModules(),
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