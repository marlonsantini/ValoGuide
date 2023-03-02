package fingerfire.com.valorant.features.weapons.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.databinding.ItemWeaponBinding
import fingerfire.com.valorant.features.weapons.data.response.WeaponDataResponse

class WeaponsAdapter(
    private var weaponList: List<WeaponDataResponse>,
    private val itemClick: (WeaponDataResponse) -> Unit
) : RecyclerView.Adapter<WeaponsAdapter.WeaponViewHolder>() {

    private var _weaponList = weaponList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        return WeaponViewHolder(
            ItemWeaponBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        with(holder) {
            with(_weaponList[position]) {
                binding.tvWeapon.text = displayName
                binding.ivWeapon.load(displayIcon) {
                    crossfade(true)
                    crossfade(1000)
                }

                binding.cvWeapons.setOnClickListener {
                    itemClick.invoke(_weaponList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return _weaponList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun search(query: String): Boolean {
        _weaponList.clear()
        _weaponList.addAll(weaponList.filter {
            it.displayName.contains(query, true)
        })
        notifyDataSetChanged()

        return _weaponList.isEmpty()
    }

    class WeaponViewHolder(val binding: ItemWeaponBinding) : RecyclerView.ViewHolder(binding.root)
}