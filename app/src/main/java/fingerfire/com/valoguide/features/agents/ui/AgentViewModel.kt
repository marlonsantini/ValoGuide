package fingerfire.com.valoguide.features.agents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valoguide.features.agents.data.repository.AgentsRepository
import fingerfire.com.valoguide.features.agents.ui.viewstate.AgentViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AgentViewModel(private val agentsRepository: AgentsRepository) : ViewModel() {

    private val _agentsState = MutableStateFlow(AgentViewState())
    val agentsState: StateFlow<AgentViewState> = _agentsState.asStateFlow()

    fun getAgents() {
        viewModelScope.launch {
            val agentResponse = agentsRepository.loadAgents()
            if (agentResponse.isSuccessful && agentResponse.body() != null) {
                _agentsState.update {
                    it.copy(sucess = agentResponse.body(), failure = false)
                }
            } else {
                _agentsState.update { it.copy(failure = true, sucess = null) }
            }
        }
    }

    fun onRoleSelected(role: String) {
        _agentsState.update { state ->
            val newRole = if (state.selectedRole == role) null else role
            state.copy(selectedRole = newRole)
        }
    }
}
