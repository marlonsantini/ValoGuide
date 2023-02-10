package fingerfire.com.valorant.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.data.model.AgentDataResponse
import fingerfire.com.valorant.databinding.ItemAgentBinding

class AgentAdapter(
    private var agentList: List<AgentDataResponse>
) : RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        return AgentViewHolder(
            ItemAgentBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        with(holder){
            with(agentList[position]){
                binding.tvAgent.text = displayName
                binding.imAgent.load(bustPortrait)
            }
        }
    }

    override fun getItemCount(): Int {
        return agentList.size
    }

    class AgentViewHolder(val binding: ItemAgentBinding) : RecyclerView.ViewHolder(binding.root)
}