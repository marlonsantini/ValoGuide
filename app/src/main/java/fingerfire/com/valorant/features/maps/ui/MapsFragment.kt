package fingerfire.com.valorant.features.maps.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fingerfire.com.valorant.R
import fingerfire.com.valorant.databinding.FragmentMapsBinding
import fingerfire.com.valorant.extensions.isInternetAvailable
import fingerfire.com.valorant.extensions.showDialogValorant
import fingerfire.com.valorant.features.maps.data.response.MapResponse
import fingerfire.com.valorant.features.maps.ui.adapter.MapsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsFragment : Fragment() {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var mapsAdapter: MapsAdapter
    private val viewModel: MapsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!requireActivity().isInternetAvailable()) {
            requireActivity().showDialogValorant(R.string.internet)
        } else {
            observerMaps()

            viewModel.getMaps()
        }
    }

    private fun observerMaps() {
        viewModel.mapsLiveData.observe(viewLifecycleOwner) { viewState ->
            if (viewState.sucess != null) {
                initRecyclerView()
                initAdapter(viewState.sucess)
            } else if (viewState.failure) {
                requireActivity().showDialogValorant(R.string.failResponse)
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvMaps.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvMaps.setHasFixedSize(true)
    }

    private fun initAdapter(mapResponse: MapResponse) {
        mapsAdapter = MapsAdapter(mapResponse.maps, itemClick = {
            it.uuid.let { uuid ->
                findNavController().navigate(
                    MapsFragmentDirections.actionMapsFragmentToMapDetailFragment(
                        uuid
                    )
                )
            }
        })
        binding.rvMaps.adapter = mapsAdapter
    }
}