package fingerfire.com.valorant.view.adapter.maps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fingerfire.com.valorant.data.response.MapDataResponse
import fingerfire.com.valorant.databinding.ItemMapBinding

class MapsAdapter(
    private var mapList: List<MapDataResponse>,
    private val itemClick: (MapDataResponse) -> Unit
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

                binding.cvMaps.setOnClickListener {
                    itemClick.invoke(mapList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mapList.size
    }

    class MapViewHolder(val binding: ItemMapBinding) : RecyclerView.ViewHolder(binding.root)

}