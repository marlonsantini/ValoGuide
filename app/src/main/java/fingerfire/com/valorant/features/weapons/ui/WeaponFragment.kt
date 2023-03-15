package fingerfire.com.valorant.features.weapons.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fingerfire.com.valorant.R
import fingerfire.com.valorant.databinding.FragmentWeaponBinding
import fingerfire.com.valorant.features.weapons.data.response.WeaponResponse
import fingerfire.com.valorant.features.weapons.ui.adapter.WeaponsAdapter
import fingerfire.com.valorant.util.Util
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeaponFragment : Fragment() {

    private lateinit var binding: FragmentWeaponBinding
    private lateinit var weaponsAdapter: WeaponsAdapter
    private val viewModel: WeaponViewModel by viewModel()

    private val util = Util()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeaponBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!util.isInternetAvailable(requireContext())) {
            util.showDialog(requireActivity(), "Verifique sua conexão")
        } else {
            observerWeapons()

            viewModel.getWeapons()
        }
    }

    private fun observerWeapons() {
        viewModel.weaponsLiveData.observe(viewLifecycleOwner) { response ->
            if(response.isSuccessful) {
                initRecyclerView()
                response.body()?.let { initAdapter(it) }
                initSearchView()
            } else if (response.errorBody() != null) {
                util.showDialog(requireActivity(), resources.getString(R.string.failResponse))
            } else {
                util.showDialog(requireActivity(), resources.getString(R.string.failResponse))
            }
        }
    }

    private fun initSearchView() {
        binding.searchWeapon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                weaponsAdapter.search(query)
                return true
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvWeapon.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvWeapon.setHasFixedSize(true)
    }

    private fun initAdapter(weaponResponse: WeaponResponse) {
        weaponsAdapter = WeaponsAdapter(weaponResponse.weapons, itemClick = {
            it.uuid.let { uuid ->
                findNavController().navigate(
                    WeaponFragmentDirections.actionWeaponsFragmentToWeaponDetailFragment(
                        uuid
                    )
                )
            }
        })
        binding.rvWeapon.adapter = weaponsAdapter
    }
}