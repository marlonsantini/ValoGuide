package fingerfire.com.valorant.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.data.model.WeaponDataResponse
import fingerfire.com.valorant.databinding.ItemWeaponBinding

class WeaponsAdapter(
    private var agentList: List<WeaponDataResponse>,
) : RecyclerView.Adapter<WeaponsAdapter.WeaponViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        return WeaponViewHolder(
            ItemWeaponBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        with(holder) {
            with(agentList[position]) {
                binding.tvWeapon.text = displayName
                binding.ivWeapon.load(displayIcon) {
                    crossfade(true)
                    crossfade(1000)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return agentList.size
    }

    class WeaponViewHolder(val binding: ItemWeaponBinding) : RecyclerView.ViewHolder(binding.root)
}