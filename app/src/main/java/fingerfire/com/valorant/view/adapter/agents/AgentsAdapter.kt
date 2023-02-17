package fingerfire.com.valorant.view.adapter.agents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.data.response.AgentDataResponse
import fingerfire.com.valorant.databinding.ItemAgentBinding

class AgentsAdapter(
    private var agentList: List<AgentDataResponse>,
    private val itemClick: (AgentDataResponse) -> Unit
) : RecyclerView.Adapter<AgentsAdapter.AgentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        return AgentViewHolder(
            ItemAgentBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        with(holder) {
            with(agentList[position]) {
                binding.tvAgent.text = displayName
                binding.imAgent.load(fullPortrait) {
                    crossfade(true)
                    crossfade(1000)
                }
                binding.imBackground.load(background) {
                    crossfade(true)
                }
                binding.tvRoleName.text = role.displayName

                binding.cvAgents.setOnClickListener {
                    itemClick.invoke(agentList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return agentList.size
    }

    class AgentViewHolder(val binding: ItemAgentBinding) : RecyclerView.ViewHolder(binding.root)

}