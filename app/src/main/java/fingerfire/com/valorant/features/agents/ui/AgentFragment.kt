package fingerfire.com.valorant.features.agents.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import fingerfire.com.valorant.databinding.FragmentAgentBinding
import fingerfire.com.valorant.features.agents.data.response.AgentResponse
import fingerfire.com.valorant.features.agents.ui.adapter.AgentsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class AgentFragment : Fragment() {

    private lateinit var binding: FragmentAgentBinding
    private lateinit var agentsAdapter: AgentsAdapter
    private val viewModel: AgentViewModel by viewModel()

    private var currentPosition = 0

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
            initRecyclerView(it)
            initAdapter(it)
            initChipGroup()
        }
    }

    private fun initRecyclerView(agentResponse: AgentResponse) {
        val pagerSnapHelper = PagerSnapHelper()

        binding.rvAgents.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvAgents.scrollToPosition(currentPosition)
        binding.rvAgents.setHasFixedSize(true)
        binding.rvAgents.onFlingListener = null

        pagerSnapHelper.attachToRecyclerView(binding.rvAgents)
        binding.indicator.attachToRecyclerView(binding.rvAgents, pagerSnapHelper)
        binding.indicator.createIndicators(agentResponse.agents.size, currentPosition)

        binding.rvAgents.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                currentPosition = (binding.rvAgents.layoutManager as LinearLayoutManager)
                    .findFirstVisibleItemPosition()
            }
        })
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
                binding.indicator.createIndicators(agentsAdapter.itemCount, 0)
            } else {
                agentsAdapter.filter("")
                binding.indicator.createIndicators(agentsAdapter.itemCount, 0)
            }
        }

        binding.chip2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agentsAdapter.filter(binding.chip2.text.toString())
                binding.indicator.createIndicators(agentsAdapter.itemCount, 0)
            } else {
                agentsAdapter.filter("")
                binding.indicator.createIndicators(agentsAdapter.itemCount, 0)
            }
        }

        binding.chip3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agentsAdapter.filter(binding.chip3.text.toString())
                binding.indicator.createIndicators(agentsAdapter.itemCount, 0)
            } else {
                agentsAdapter.filter("")
                binding.indicator.createIndicators(agentsAdapter.itemCount, 0)
            }
        }

        binding.chip4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agentsAdapter.filter(binding.chip4.text.toString())
                binding.indicator.createIndicators(agentsAdapter.itemCount, 0)
            } else {
                agentsAdapter.filter("")
                binding.indicator.createIndicators(agentsAdapter.itemCount, 0)
            }
        }
    }
}
