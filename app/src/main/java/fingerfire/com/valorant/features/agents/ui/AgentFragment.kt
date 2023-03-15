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
import fingerfire.com.valorant.features.agents.data.response.AgentResponse
import fingerfire.com.valorant.features.agents.ui.adapter.AgentsAdapter
import fingerfire.com.valorant.util.Util
import org.koin.androidx.viewmodel.ext.android.viewModel


class AgentFragment : Fragment() {

    private lateinit var binding: FragmentAgentBinding
    private lateinit var agentsAdapter: AgentsAdapter
    private val viewModel: AgentViewModel by viewModel()

    private var currentPosition = 0
    private val util = Util()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!util.isInternetAvailable(requireContext())) {
            util.showDialog(requireActivity(), "Verifique sua conexão")
        } else {
            observerAgents()

            viewModel.getAgents()
        }
    }

    private fun observerAgents() {
        viewModel.agentsLiveData.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { initRecyclerView(it) }
                response.body()?.let { initAdapter(it) }
                initChipGroup()
                util.initAdMob(binding.adView)
            } else if (response.errorBody() != null) {
                util.showDialog(requireActivity(), resources.getString(R.string.failResponse))
            } else {
                util.showDialog(requireActivity(), resources.getString(R.string.failResponse))
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
            if (isChecked) {
                agentsAdapter.filter(binding.initiator.text.toString())
                scrollCurrentPositionChip()
            } else {
                agentsAdapter.filter("")
                scrollCurrentPositionChip()
            }
        }

        binding.controller.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agentsAdapter.filter(binding.controller.text.toString())
                scrollCurrentPositionChip()
            } else {
                agentsAdapter.filter("")
                scrollCurrentPositionChip()
            }
        }

        binding.sentinel.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agentsAdapter.filter(binding.sentinel.text.toString())
                scrollCurrentPositionChip()
            } else {
                agentsAdapter.filter("")
                scrollCurrentPositionChip()
            }
        }

        binding.duelist.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                agentsAdapter.filter(binding.duelist.text.toString())
                scrollCurrentPositionChip()
            } else {
                agentsAdapter.filter("")
                scrollCurrentPositionChip()
            }
        }
    }

    private fun scrollCurrentPositionChip() {
        currentPosition = 0
        binding.rvAgents.scrollToPosition(currentPosition)
        binding.indicator.createIndicators(agentsAdapter.itemCount, currentPosition)
    }
}
