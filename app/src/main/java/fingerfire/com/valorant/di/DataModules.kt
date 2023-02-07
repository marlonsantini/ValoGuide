package fingerfire.com.valorant.di

import fingerfire.com.valorant.data.repository.AgentsRepository
import org.koin.dsl.module
/** Classe de modulo feito para as injeção de dependencia do repositorio onde no exemplo ela está
 * fazendo a verificação se está na lista de DI e usando factory
 * nesse caso sendo encerrado no momento em que parar de ter uso */
class DataModules {
    fun getDataModules() = module {
        factory {
            AgentsRepository(get())
        }
    }
}