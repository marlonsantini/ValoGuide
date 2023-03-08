package fingerfire.com.valorant.features.maps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import fingerfire.com.valorant.databinding.ItemMapBinding
import fingerfire.com.valorant.features.maps.data.response.MapDataResponse

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
                    log()
                    itemClick.invoke(mapList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mapList.size
    }

    private fun log(){
        val firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, 10)
            param(FirebaseAnalytics.Param.ITEM_NAME, "camisa")
        }
        firebaseAnalytics.setUserProperty("favorite_car", "HB20")

    }

    class MapViewHolder(val binding: ItemMapBinding) : RecyclerView.ViewHolder(binding.root)

}