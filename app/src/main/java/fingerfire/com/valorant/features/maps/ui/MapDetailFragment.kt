package fingerfire.com.valorant.features.maps.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import fingerfire.com.valorant.databinding.FragmentMapDetailBinding
import fingerfire.com.valorant.features.maps.data.response.MapDataResponse
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapDetailFragment : Fragment() {

    private lateinit var binding: FragmentMapDetailBinding
    private val args: MapDetailFragmentArgs by navArgs()
    private val viewModel: MapDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMapDetail(args.uuid)
        initMapDetailObserve()
        initBackButtonClickListener()

    }

    private fun initBackButtonClickListener() {
        binding.backButtonImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initUi(mapDataResponse: MapDataResponse) {
        binding.apply {
            mapDataResponse.let { item ->
                binding.mapImageView.load(item.displayIcon)
                binding.mapCoordinateTextView.text = item.coordinates
                binding.mapNameTextView.text = item.displayName
            }
        }
    }

    private fun initMapDetailObserve() {
        viewModel.mapsDetailLiveData.observe(viewLifecycleOwner) {
            initUi(it.data)
        }
    }
}
