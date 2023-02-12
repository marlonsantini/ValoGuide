package fingerfire.com.valorant.di

import fingerfire.com.valorant.data.repository.ValorantRepository
import org.koin.dsl.module

class DataModules {
    fun getDataModules() = module {
        factory {
            ValorantRepository(get())
        }
    }
}