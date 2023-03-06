package fingerfire.com.valorant.features.agents.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.databinding.ItemAbilitiesBinding
import fingerfire.com.valorant.features.agents.data.response.AbilitiesResponse
import fingerfire.com.valorant.util.Util

class AbilitiesAdapter(
    private val abilitiesList: List<AbilitiesResponse>
) : RecyclerView.Adapter<AbilitiesAdapter.AbilitiesViewHolder>() {

    private val util = Util()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        return AbilitiesViewHolder(
            ItemAbilitiesBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        with(holder) {
            with(abilitiesList[position]) {
                binding.abilityTitleTextView.text = displayName
                binding.abilityImageView.load(displayIcon)
                binding.abilityDescriptionTextView.text = description

                val drawable = util.createGradientDrawable("#ba89ffff", "#6241ccff", "#38328eff")
                binding.abilityImageView.background = drawable
            }
        }
    }

    override fun getItemCount(): Int {
        return abilitiesList.size
    }

    class AbilitiesViewHolder(val binding: ItemAbilitiesBinding) :
        RecyclerView.ViewHolder(binding.root)
}