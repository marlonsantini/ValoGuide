package fingerfire.com.valorant.features.weapons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.features.weapons.data.response.SkinsResponse
import fingerfire.com.valorant.databinding.ItemWeaponSkinBinding


class SkinsAdapter(
    private val skinsList: MutableList<SkinsResponse>
) : RecyclerView.Adapter<SkinsAdapter.SkinsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinsViewHolder {
        return SkinsViewHolder(
            ItemWeaponSkinBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SkinsViewHolder, position: Int) {
        with(holder) {
            with(skinsList[position]) {
                skinsList.removeIf { it.displayIcon.isNullOrEmpty() || it.contentTierUuid.isNullOrEmpty() }
                binding.skinImageView.load(displayIcon)
                binding.skinNameTextView.text = displayName
            }
        }
    }

    override fun getItemCount(): Int {
        return skinsList.size
    }

    class SkinsViewHolder(val binding: ItemWeaponSkinBinding) :
        RecyclerView.ViewHolder(binding.root)
}