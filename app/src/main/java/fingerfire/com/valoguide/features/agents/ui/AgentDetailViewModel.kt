package fingerfire.com.valoguide.features.agents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valoguide.features.agents.data.repository.AgentsRepository
import fingerfire.com.valoguide.features.agents.ui.viewstate.AgentDetailViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AgentDetailViewModel(private val agentsRepository: AgentsRepository) : ViewModel() {

    private val _agentDetailState = MutableStateFlow(AgentDetailViewState())
    val agentDetailState: StateFlow<AgentDetailViewState> = _agentDetailState.asStateFlow()

    fun getAgentDetail(uuid: String) {
        viewModelScope.launch {
            try {
                val response = agentsRepository.getAgentDetail(uuid)
                _agentDetailState.value = AgentDetailViewState(sucess = response)
            } catch (_: Exception) {
                _agentDetailState.value = AgentDetailViewState(failure = true)
            }
        }
    }
}
