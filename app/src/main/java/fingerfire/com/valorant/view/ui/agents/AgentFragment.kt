package fingerfire.com.valorant.view.ui.agents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fingerfire.com.valorant.R
import fingerfire.com.valorant.databinding.FragmentHomeBinding
import fingerfire.com.valorant.view.adapter.AgentsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgentFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: AgentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
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
            binding.rvAgents.adapter = AgentsAdapter(it.agents)
        }
    }

    private fun initRecyclerView() {
        binding.rvAgents.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvAgents.setHasFixedSize(true)
    }
}