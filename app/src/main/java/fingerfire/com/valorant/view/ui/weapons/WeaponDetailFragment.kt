package fingerfire.com.valorant.view.ui.weapons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import fingerfire.com.valorant.data.model.WeaponDataResponse
import fingerfire.com.valorant.databinding.FragmentWeaponDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeaponDetailFragment : Fragment() {

    private lateinit var binding: FragmentWeaponDetailBinding
    private val args: WeaponDetailFragmentArgs by navArgs()
    private val viewModel: WeaponDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeaponDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWeaponDetail(args.uuid)
        initWeaponDetailObserve()
        initBackButtonClickListener()
    }

    private fun initBackButtonClickListener() {
        binding.backButtonImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initUi(weaponDataResponse: WeaponDataResponse) {
        binding.apply {
            weaponDataResponse.let { item ->
                binding.weaponIconImageView.load(item.displayIcon)

            }
        }
    }

    private fun initWeaponDetailObserve() {
        viewModel.weaponsDetailLiveData.observe(viewLifecycleOwner) {
            initUi(it.data)
        }
    }
}