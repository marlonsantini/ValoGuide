package fingerfire.com.valorant.features.weapons.di

import fingerfire.com.valorant.features.weapons.data.repository.WeaponsRepository
import org.koin.dsl.module

class WeaponsDataModules {
    fun getWeaponsDataModules() = module {
        factory {
            WeaponsRepository(get())
        }
    }
}