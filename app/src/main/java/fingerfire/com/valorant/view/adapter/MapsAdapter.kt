package fingerfire.com.valorant.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.data.model.MapDataResponse
import fingerfire.com.valorant.databinding.ItemMapBinding

class MapsAdapter(
    private var mapList: List<MapDataResponse>,
) : RecyclerView.Adapter<MapsAdapter.MapViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        return MapViewHolder(
            ItemMapBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        with(holder) {
            with(mapList[position]) {
                binding.tvMap.text = displayName
                binding.ivMap.load(splash) {
                    crossfade(true)
                }

            }
        }
    }


    override fun getItemCount(): Int {
        return mapList.size
    }

    class MapViewHolder(val binding: ItemMapBinding) : RecyclerView.ViewHolder(binding.root)

}