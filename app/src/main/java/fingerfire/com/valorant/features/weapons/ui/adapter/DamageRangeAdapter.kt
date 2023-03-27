package fingerfire.com.valorant.features.weapons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fingerfire.com.valorant.databinding.ItemWeaponDamageRangeBinding
import fingerfire.com.valorant.features.weapons.data.response.DamageRangesResponse

class DamageRangeAdapter(
    private val damageRangeList: MutableList<DamageRangesResponse>
) : RecyclerView.Adapter<DamageRangeAdapter.DamageRangeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DamageRangeViewHolder {
        return DamageRangeViewHolder(
            ItemWeaponDamageRangeBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DamageRangeViewHolder, position: Int) {
        with(holder) {
            with(damageRangeList[position]) {
                val concatenatedString = rangeStartMeters + " - " + rangeEndMeters + "M"
                binding.tvMeter.text = concatenatedString
                binding.tvHead.text = headDamage.toInt().toString()
                binding.tvBody.text = bodyDamage.toInt().toString()
                binding.tvLeg.text = legDamage.toInt().toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return damageRangeList.size
    }


    class DamageRangeViewHolder(val binding: ItemWeaponDamageRangeBinding) :
        RecyclerView.ViewHolder(binding.root)
}