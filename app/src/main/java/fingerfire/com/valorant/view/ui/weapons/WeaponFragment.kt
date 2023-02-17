package fingerfire.com.valorant.view.ui.weapons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fingerfire.com.valorant.databinding.FragmentWeaponBinding
import fingerfire.com.valorant.view.adapter.weapons.WeaponsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeaponFragment : Fragment() {

    private lateinit var binding: FragmentWeaponBinding
    private lateinit var weaponsAdapter: WeaponsAdapter
    private val viewModel: WeaponViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeaponBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerWeapons()

        viewModel.getWeapons()
    }

    private fun observerWeapons() {
        viewModel.weaponsLiveData.observe(viewLifecycleOwner) {
            initRecyclerView()
            initAdapter()
        }
    }

    private fun initRecyclerView() {
        binding.rvWeapon.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvWeapon.setHasFixedSize(true)
    }

    private fun initAdapter() {
        viewModel.weaponsLiveData.value?.let { it ->
            weaponsAdapter = WeaponsAdapter(it.weapons, itemClick = {
                it.uuid.let { uuid ->
                    findNavController().navigate(WeaponFragmentDirections.actionWeaponsFragmentToWeaponDetailFragment(uuid))
                }
            })
            binding.rvWeapon.adapter = weaponsAdapter
        }
    }
}