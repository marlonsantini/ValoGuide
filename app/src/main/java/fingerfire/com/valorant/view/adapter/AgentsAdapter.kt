package fingerfire.com.valorant.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.data.model.AgentDataResponse
import fingerfire.com.valorant.databinding.ItemAgentBinding

class AgentsAdapter(
    private var agentList: List<AgentDataResponse>,
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
            }
        }
    }

    override fun getItemCount(): Int {
        return agentList.size
    }

    class AgentViewHolder(val binding: ItemAgentBinding) : RecyclerView.ViewHolder(binding.root)

}