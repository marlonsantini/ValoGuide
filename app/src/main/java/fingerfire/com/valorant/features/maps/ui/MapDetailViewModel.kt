package fingerfire.com.valorant.features.maps.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.features.maps.data.repository.MapsRepository
import fingerfire.com.valorant.features.maps.data.response.MapDetailResponse
import kotlinx.coroutines.launch

class MapDetailViewModel(private val mapsRepository: MapsRepository) : ViewModel() {

    private val mapsDetailMutableLiveData: MutableLiveData<MapDetailResponse> =
        MutableLiveData<MapDetailResponse>()
    val mapsDetailLiveData: LiveData<MapDetailResponse>
        get() {
            return mapsDetailMutableLiveData
        }

    fun getMapDetail(uuid: String) {
        viewModelScope.launch {
            val mapDetailResponse = mapsRepository.getMapDetail(uuid)
            mapsDetailMutableLiveData.postValue(mapDetailResponse)
        }
    }
}