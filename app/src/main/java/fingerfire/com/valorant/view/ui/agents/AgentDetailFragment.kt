package fingerfire.com.valorant.view.ui.agents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import fingerfire.com.valorant.data.model.AgentDataResponse
import fingerfire.com.valorant.databinding.FragmentAgentDetailBinding
import fingerfire.com.valorant.view.adapter.agents.AbilitiesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgentDetailFragment : Fragment() {

    private lateinit var binding: FragmentAgentDetailBinding
    private val args: AgentDetailFragmentArgs by navArgs()
    private val viewModel: AgentDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAgentDetail(args.uuid)
        initAgentDetailObserve()
        initBackButtonClickListener()

    }

    private fun initBackButtonClickListener() {
        binding.backButtonImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initUi(agentDataResponse: AgentDataResponse) {
        binding.apply {
            agentDataResponse.let { item ->
                binding.agentIconImageView.load(item.fullPortrait)
                binding.tvAgentName.text = item.displayName
                tvRole.text = item.role.displayName
                binding.tvDesc.text = item.description
                binding.rvAbilities.adapter = AbilitiesAdapter(item.abilities)
            }
        }
    }

    private fun initAgentDetailObserve() {
        viewModel.agentsDetailLiveData.observe(viewLifecycleOwner) {
            initUi(it.data)
        }
    }
}