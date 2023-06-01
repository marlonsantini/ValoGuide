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
import fingerfire.com.valorant.R
import fingerfire.com.valorant.databinding.FragmentAgentBinding
import fingerfire.com.valorant.extensions.initAdMob
import fingerfire.com.valorant.extensions.isInternetAvailable
import fingerfire.com.valorant.extensions.showDialogValorant
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
        if (!requireActivity().isInternetAvailable()) {
            requireActivity().showDialogValorant(R.string.internet)
        } else {
            observerAgents()

            viewModel.getAgents()
        }
    }

    private fun observerAgents() {
        viewModel.agentsLiveData.observe(viewLifecycleOwner) { viewState ->
            if (viewState.sucess != null) {
                initRecyclerView(viewState.sucess)
                initAdapter(viewState.sucess)
                initChipGroup()
                binding.adView.initAdMob()
            } else if (viewState.failure) {
                requireActivity().showDialogValorant(R.string.failResponse)
            }
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
        binding.initiator.setOnCheckedChangeListener { _, isChecked ->
            filterList(isChecked, binding.initiator.text.toString())
        }

        binding.controller.setOnCheckedChangeListener { _, isChecked ->
            filterList(isChecked, binding.controller.text.toString())
        }

        binding.sentinel.setOnCheckedChangeListener { _, isChecked ->
            filterList(isChecked, binding.sentinel.text.toString())
        }

        binding.duelist.setOnCheckedChangeListener { _, isChecked ->
            filterList(isChecked, binding.duelist.text.toString())
        }
    }

    private fun filterList(isChecked: Boolean, textToFilter: String) {
        if (isChecked) {
            agentsAdapter.filter(textToFilter)
            scrollCurrentPositionChip()
        } else {
            agentsAdapter.filter("")
            scrollCurrentPositionChip()
        }
    }

    private fun scrollCurrentPositionChip() {
        currentPosition = 0
        binding.rvAgents.scrollToPosition(currentPosition)
        binding.indicator.createIndicators(agentsAdapter.itemCount, currentPosition)
    }
}
