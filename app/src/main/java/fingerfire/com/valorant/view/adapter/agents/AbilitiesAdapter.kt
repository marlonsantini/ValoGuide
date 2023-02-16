package fingerfire.com.valorant.view.adapter.agents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.data.model.AbilitiesResponse
import fingerfire.com.valorant.databinding.ItemAbilitiesBinding

class AbilitiesAdapter(
    private val abilitiesList: List<AbilitiesResponse>
) : RecyclerView.Adapter<AbilitiesAdapter.AbilitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        return AbilitiesViewHolder(
            ItemAbilitiesBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        with(holder){
            with(abilitiesList[position]) {
                binding.abilityTitleTextView.text = displayName
                binding.abilityImageView.load(displayIcon)
                binding.abilityDescriptionTextView.text = description
            }
        }

    }

    override fun getItemCount(): Int {
        return abilitiesList.size
    }

    class AbilitiesViewHolder(val binding: ItemAbilitiesBinding) : RecyclerView.ViewHolder(binding.root)
}