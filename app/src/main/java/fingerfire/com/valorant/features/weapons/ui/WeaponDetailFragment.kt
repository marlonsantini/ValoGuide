package fingerfire.com.valorant.features.weapons.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import fingerfire.com.valorant.databinding.FragmentWeaponDetailBinding
import fingerfire.com.valorant.features.weapons.data.response.WeaponDetailDataResponse
import fingerfire.com.valorant.features.weapons.ui.adapter.DamageRangeAdapter
import fingerfire.com.valorant.features.weapons.ui.adapter.SkinsAdapter
import fingerfire.com.valorant.util.Util
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeaponDetailFragment : Fragment() {

    private lateinit var binding: FragmentWeaponDetailBinding
    private val args: WeaponDetailFragmentArgs by navArgs()
    private val viewModel: WeaponDetailViewModel by viewModel()

    private val util = Util()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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

    private fun initUi(weaponDetailDataResponse: WeaponDetailDataResponse) {
        binding.apply {
            weaponDetailDataResponse.let { item ->
                binding.weaponIconImageView.load(item.displayIcon)
                binding.weaponNameTextView.text = item.displayName
                binding.rvSkins.adapter = SkinsAdapter(item.skins)


                if (item.displayName != "Confronto") {
                    binding.weaponCategoryTextView.text = item.shopData.categoryText

                    binding.rvDamageRange.adapter =
                        context?.let { DamageRangeAdapter(item.weaponStats.damageRanges) }

                } else {
                    binding.damageRangeContainer.visibility = View.GONE
                }

                val drawable = util.createGradientDrawable("#000000", "#AA9B9B", "#C0ACA6")
                binding.weaponIconImageView.background = drawable
            }
        }
    }

    private fun initWeaponDetailObserve() {
        viewModel.weaponsDetailLiveData.observe(viewLifecycleOwner) {
            initUi(it.data)
        }
    }
}