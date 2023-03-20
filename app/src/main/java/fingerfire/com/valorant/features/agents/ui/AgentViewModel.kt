package fingerfire.com.valorant.features.agents.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.features.agents.data.repository.AgentsRepository
import fingerfire.com.valorant.features.agents.ui.viewstate.AgentViewState
import kotlinx.coroutines.launch

class AgentViewModel(private val agentsRepository: AgentsRepository) : ViewModel() {

    private val agentsMutableLiveData: MutableLiveData<AgentViewState> =
        MutableLiveData<AgentViewState>()
    val agentsLiveData: LiveData<AgentViewState>
        get() {
            return agentsMutableLiveData
        }

    fun getAgents() {
        viewModelScope.launch {
            val agentResponse = agentsRepository.loadAgents()
            if (agentResponse.isSuccessful && agentResponse.body() != null) {
                agentsMutableLiveData.postValue(AgentViewState(sucess = agentResponse.body()))
            } else if (agentResponse.errorBody() != null) {
                agentsMutableLiveData.postValue(AgentViewState(failure = true))
            }
        }
    }
}