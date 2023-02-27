package fingerfire.com.valorant.view.adapter.agents

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.data.response.agents.AgentDataResponse
import fingerfire.com.valorant.databinding.ItemAgentBinding

class AgentsAdapter(
    private var agentList: List<AgentDataResponse>,
    private val itemClick: (AgentDataResponse) -> Unit
) : RecyclerView.Adapter<AgentsAdapter.AgentViewHolder>() {

    private var _agentList = agentList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        return AgentViewHolder(
            ItemAgentBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        with(holder) {
            with(_agentList[position]) {
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
                    itemClick.invoke(_agentList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return _agentList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun search(query: String): Boolean {
        _agentList.clear()
        _agentList.addAll(agentList.filter {
            it.displayName.contains(query, true)
        })
        notifyDataSetChanged()

        return _agentList.isEmpty()
    }

    class AgentViewHolder(val binding: ItemAgentBinding) : RecyclerView.ViewHolder(binding.root)

}