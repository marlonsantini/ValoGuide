package fingerfire.com.valorant.view.ui.weapons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fingerfire.com.valorant.databinding.FragmentWeaponBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeaponFragment : Fragment() {

    private lateinit var binding: FragmentWeaponBinding
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
            binding.tvWeapons.text = it.weapons[1].displayName

        }
    }
}