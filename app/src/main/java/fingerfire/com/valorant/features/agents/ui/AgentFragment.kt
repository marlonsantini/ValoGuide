package fingerfire.com.valorant.features.agents.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fingerfire.com.valorant.R
import fingerfire.com.valorant.databinding.FragmentAgentBinding
import fingerfire.com.valorant.features.agents.data.response.AgentResponse
import fingerfire.com.valorant.features.agents.ui.adapter.AgentsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgentFragment : Fragment(R.layout.fragment_agent) {

    private lateinit var binding: FragmentAgentBinding
    private lateinit var agentsAdapter: AgentsAdapter
    private val viewModel: AgentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerAgents()

        viewModel.getAgents()
    }

    private fun observerAgents() {
        viewModel.agentsLiveData.observe(viewLifecycleOwner) {
            initRecyclerView()
            initAdapter(it)
            initChipGroup()
        }
    }

    private fun initRecyclerView() {
        binding.rvAgents.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvAgents.setHasFixedSize(true)
    }

    private fun initAdapter(agentResponse: AgentResponse) {
        agentsAdapter = AgentsAdapter(agentResponse.agents, itemClick = {
            it.uuid.let { uuid ->
                findNavController().navigate(
                    AgentFragmentDirections.actionAgentsFragmentToAgentDetailFragment(
                        uuid
                    )
                )
            }
        })
        binding.rvAgents.adapter = agentsAdapter
    }

    private fun initChipGroup() {
        binding.chipGroup.clearCheck()
        binding.chip1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agentsAdapter.filter(binding.chip1.text.toString())
            } else {
                agentsAdapter.filter("")
            }
        }

        binding.chip2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agentsAdapter.filter(binding.chip2.text.toString())
            } else {
                agentsAdapter.filter("")
            }
        }

        binding.chip3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agentsAdapter.filter(binding.chip3.text.toString())
            } else {
                agentsAdapter.filter("")
            }
        }

        binding.chip4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agentsAdapter.filter(binding.chip4.text.toString())
            } else {
                agentsAdapter.filter("")
            }
        }
    }
}
