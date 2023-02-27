package fingerfire.com.valorant.features.maps.di

import fingerfire.com.valorant.features.maps.ui.MapDetailViewModel
import fingerfire.com.valorant.features.maps.ui.MapsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MapsUiModules {

    fun getViewModules() = module {
        viewModel {
            MapDetailViewModel(get())
        }
        viewModel {
            MapsViewModel(get())
        }
    }
}