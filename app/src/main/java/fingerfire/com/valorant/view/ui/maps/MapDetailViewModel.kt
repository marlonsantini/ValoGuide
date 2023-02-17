package fingerfire.com.valorant.view.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.data.response.maps.MapDetailResponse
import fingerfire.com.valorant.data.repository.ValorantRepository
import kotlinx.coroutines.launch

class MapDetailViewModel(private val mapsRepository: ValorantRepository) : ViewModel() {

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