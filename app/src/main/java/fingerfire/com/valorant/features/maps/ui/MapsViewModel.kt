package fingerfire.com.valorant.features.maps.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.features.maps.data.response.MapResponse
import fingerfire.com.valorant.features.maps.data.repository.MapsRepository
import kotlinx.coroutines.launch

class MapsViewModel(private val mapsRepository: MapsRepository) : ViewModel() {
    private val mapsMutableLiveData: MutableLiveData<MapResponse> = MutableLiveData<MapResponse>()
    val mapsLiveData: LiveData<MapResponse>
    get() {
        return mapsMutableLiveData
    }

    fun getMaps() {
        viewModelScope.launch {
            val mapResponse = mapsRepository.loadMaps()
            mapsMutableLiveData.postValue(mapResponse)
        }
    }
}