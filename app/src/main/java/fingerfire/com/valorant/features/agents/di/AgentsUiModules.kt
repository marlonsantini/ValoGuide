package fingerfire.com.valorant.features.agents.di

import fingerfire.com.valorant.features.agents.ui.AgentDetailViewModel
import fingerfire.com.valorant.features.agents.ui.AgentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class AgentsUiModules {
    fun getViewModules() = module {
        viewModel {
            AgentViewModel(get())
        }
        viewModel {
            AgentDetailViewModel(get())
        }
    }
}