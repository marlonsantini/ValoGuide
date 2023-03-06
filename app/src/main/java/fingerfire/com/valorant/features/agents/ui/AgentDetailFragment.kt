package fingerfire.com.valorant.features.agents.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import fingerfire.com.valorant.R
import fingerfire.com.valorant.databinding.FragmentAgentDetailBinding
import fingerfire.com.valorant.features.agents.data.response.AgentDataResponse
import fingerfire.com.valorant.features.agents.ui.adapter.AbilitiesAdapter
import fingerfire.com.valorant.util.Util
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgentDetailFragment : Fragment() {

    private lateinit var binding: FragmentAgentDetailBinding
    private val args: AgentDetailFragmentArgs by navArgs()
    private val viewModel: AgentDetailViewModel by viewModel()

    private val util = Util()

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

                binding.ivVoice.setOnClickListener {
                    startVoice(item.voiceLine.mediaList[0].wave)
                }

                val startColor = "#" + item.backgroundGradientColors[0].substring(0, 6)
                val centerColor = "#" + item.backgroundGradientColors[1].substring(0, 6)
                val endColor = "#" + item.backgroundGradientColors[2].substring(0, 6)
                val drawable = util.createGradientDrawable(startColor, centerColor, endColor)
                binding.agentIconImageView.background = drawable
            }
        }
    }

    private fun startVoice(wav: String) {
        val mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(wav)
        mediaPlayer.prepare()
        mediaPlayer.start()
        binding.ivVoice.setColorFilter(ContextCompat.getColor(requireContext(), R.color.main_red))
        mediaPlayer.setOnCompletionListener {
            binding.ivVoice.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
        }
    }

    private fun initAgentDetailObserve() {
        viewModel.agentsDetailLiveData.observe(viewLifecycleOwner) {
            initUi(it.data)
        }
    }
}