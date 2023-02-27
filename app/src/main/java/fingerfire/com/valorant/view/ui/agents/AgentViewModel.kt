package fingerfire.com.valorant.view.ui.agents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.data.response.agents.AgentResponse
import fingerfire.com.valorant.data.repository.ValorantRepository
import kotlinx.coroutines.launch

class AgentViewModel(private val agentsRepository: ValorantRepository) : ViewModel() {

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