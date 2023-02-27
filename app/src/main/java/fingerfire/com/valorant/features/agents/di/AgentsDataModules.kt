package fingerfire.com.valorant.features.agents.di

import fingerfire.com.valorant.features.agents.data.repository.AgentsRepository
import org.koin.dsl.module

class AgentsDataModules {
    fun getAgentsDataModules() = module {
        factory {
            AgentsRepository(get())
        }
    }
}