package fingerfire.com.valorant.view.ui.agents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.data.response.agents.AgentDetailResponse
import fingerfire.com.valorant.data.repository.ValorantRepository
import kotlinx.coroutines.launch

class AgentDetailViewModel(private val agentsRepository: ValorantRepository) : ViewModel() {

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