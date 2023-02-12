package fingerfire.com.valorant.view.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fingerfire.com.valorant.databinding.FragmentMapsBinding
import fingerfire.com.valorant.view.adapter.MapsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsFragment : Fragment() {

    private lateinit var binding: FragmentMapsBinding
    private val viewModel: MapsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerAgents()

        viewModel.getMaps()
    }

    private fun observerAgents() {
        viewModel.mapsLiveData.observe(viewLifecycleOwner) {
            initRecyclerView()
            binding.rvMaps.adapter = MapsAdapter(it.maps)

        }
    }

    private fun initRecyclerView() {
        binding.rvMaps.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvMaps.setHasFixedSize(true)
    }

}