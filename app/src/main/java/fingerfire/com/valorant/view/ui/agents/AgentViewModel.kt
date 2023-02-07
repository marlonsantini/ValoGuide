package fingerfire.com.valorant.view.ui.agents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.data.model.AgentResponse
import fingerfire.com.valorant.data.repository.ValorantRepository
import kotlinx.coroutines.launch

/** Classe viewmodel com as regras de negocio
 * já com injeção de dependencia, coroutines e livedata */
class AgentViewModel(private val agentsRepository: ValorantRepository) : ViewModel() {
    /**
    MutableLiveData é uma função que recebe objetos que podem ser alterados.
    LiveData é uma função que recebe objetos que NÃO podem ser alterados.
    */
    private val agentsMutableLiveData: MutableLiveData<AgentResponse> = MutableLiveData<AgentResponse>()
    val agentsLiveData: LiveData<AgentResponse>
    //uso do get
        get() {
           return agentsMutableLiveData
        }

    fun getAgents() {
        /**
        Chamada do ViewScope.launch do Coroutines para dizer que isso vai executar em outra thread
        e não na principal
        */
        viewModelScope.launch {
            val agentResponse = agentsRepository.loadAgents()
            agentsMutableLiveData.postValue(agentResponse)
        }
    }
}