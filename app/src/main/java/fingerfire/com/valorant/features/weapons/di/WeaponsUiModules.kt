package fingerfire.com.valorant.features.weapons.di

import fingerfire.com.valorant.features.weapons.ui.WeaponDetailViewModel
import fingerfire.com.valorant.features.weapons.ui.WeaponViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class WeaponsUiModules {
    fun getWeaponsUiModules() = module {
        viewModel {
            WeaponViewModel(get())
        }
        viewModel {
            WeaponDetailViewModel(get())
        }
    }
}