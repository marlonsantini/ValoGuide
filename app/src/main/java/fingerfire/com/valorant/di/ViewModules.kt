package fingerfire.com.valorant.di

import fingerfire.com.valorant.view.ui.agents.AgentDetailViewModel
import fingerfire.com.valorant.view.ui.agents.AgentViewModel
import fingerfire.com.valorant.view.ui.maps.MapDetailViewModel
import fingerfire.com.valorant.view.ui.maps.MapsViewModel
import fingerfire.com.valorant.view.ui.weapons.WeaponDetailViewModel
import fingerfire.com.valorant.view.ui.weapons.WeaponViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModules {

    fun getViewModules() = module {
        viewModel {
            AgentViewModel(get())
        }
        viewModel {
            AgentDetailViewModel(get())
        }
        viewModel {
            WeaponViewModel(get())
        }
        viewModel {
            WeaponDetailViewModel(get())
        }
        viewModel {
            MapDetailViewModel(get())
        }
        viewModel {
            MapsViewModel(get())
        }
    }
}