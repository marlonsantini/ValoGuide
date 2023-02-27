package fingerfire.com.valorant.features.agents.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.features.agents.data.repository.AgentsRepository
import fingerfire.com.valorant.features.agents.data.response.AgentResponse
import kotlinx.coroutines.launch

class AgentViewModel(private val agentsRepository: AgentsRepository) : ViewModel() {

    private val agentsMutableLiveData: MutableLiveData<AgentResponse> =
        MutableLiveData<AgentResponse>()
    val agentsLiveData: LiveData<AgentResponse>
        get() {
            return agentsMutableLiveData
        }

    fun getAgents() {
        viewModelScope.launch {
            val agentResponse = agentsRepository.loadAgents()
            agentsMutableLiveData.postValue(agentResponse)
        }
    }
}