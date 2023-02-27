package fingerfire.com.valorant.features.agents.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.features.agents.data.repository.AgentsRepository
import fingerfire.com.valorant.features.agents.data.response.AgentDetailResponse
import kotlinx.coroutines.launch

class AgentDetailViewModel(private val agentsRepository: AgentsRepository) : ViewModel() {

    private val agentsDetailMutableLiveData: MutableLiveData<AgentDetailResponse> =
        MutableLiveData<AgentDetailResponse>()
    val agentsDetailLiveData: LiveData<AgentDetailResponse>
        get() {
            return agentsDetailMutableLiveData
        }

    fun getAgentDetail(uuid: String) {
        viewModelScope.launch {
            val agentDetailResponse = agentsRepository.getAgentDetail(uuid)
            agentsDetailMutableLiveData.postValue(agentDetailResponse)
        }
    }
}