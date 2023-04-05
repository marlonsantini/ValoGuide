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
import fingerfire.com.valorant.extensions.createGradientDrawable
import fingerfire.com.valorant.extensions.initAdMob
import fingerfire.com.valorant.features.weapons.data.response.DamageRangesResponse
import fingerfire.com.valorant.features.weapons.data.response.WeaponDetailDataResponse
import fingerfire.com.valorant.features.weapons.ui.adapter.DamageRangeAdapter
import fingerfire.com.valorant.features.weapons.ui.adapter.SkinsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeaponDetailFragment : Fragment() {

    private lateinit var binding: FragmentWeaponDetailBinding
    private val args: WeaponDetailFragmentArgs by navArgs()
    private val viewModel: WeaponDetailViewModel by viewModel()

    private val fakeDamageRanges = mutableListOf(
        DamageRangesResponse("eRRor", "eRRor", 0.0, 0.0, 0.0)
    )

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


                if (item.uuid != WEAPON_MELEE) {
                    if (item.shopData != null) {
                        binding.weaponCategoryTextView.text = item.shopData.categoryText
                    } else {
                        binding.weaponCategoryTextView.text = ""
                    }

                    binding.rvDamageRange.adapter = DamageRangeAdapter(
                        item.weaponStats?.damageRanges ?: fakeDamageRanges
                    )


                } else {
                    binding.damageRangeContainer.visibility = View.GONE
                    binding.weaponCategoryTextView.visibility = View.GONE
                }

                val drawable = createGradientDrawable("#000000", "#AA9B9B", "#C0ACA6")
                binding.weaponIconImageView.background = drawable
            }
        }
    }

    private fun initWeaponDetailObserve() {
        viewModel.weaponsDetailLiveData.observe(viewLifecycleOwner) {
            initUi(it.data)
            binding.adView.initAdMob()
        }
    }

    companion object {
        private const val WEAPON_MELEE = "2f59173c-4bed-b6c3-2191-dea9b58be9c7"
    }
}