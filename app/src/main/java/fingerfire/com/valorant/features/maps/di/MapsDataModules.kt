package fingerfire.com.valorant.features.maps.di

import fingerfire.com.valorant.features.maps.data.repository.MapsRepository
import org.koin.dsl.module

class MapsDataModules {
    fun getMapsDataModules() = module {
        factory {
            MapsRepository(get())
        }
    }
}