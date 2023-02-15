package fingerfire.com.valorant.di

import fingerfire.com.valorant.view.ui.agents.AgentDetailViewModel
import fingerfire.com.valorant.view.ui.agents.AgentViewModel
import fingerfire.com.valorant.view.ui.maps.MapsViewModel
import fingerfire.com.valorant.view.ui.weapons.WeaponViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModules {

    fun getViewModules() = module {
        viewModel {
            AgentViewModel(get())
        }
        viewModel {
            WeaponViewModel(get())
        }
        viewModel {
            MapsViewModel(get())
        }
        viewModel {
            AgentDetailViewModel(get())
        }
    }
}