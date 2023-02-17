package fingerfire.com.valorant.view.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.data.response.maps.MapResponse
import fingerfire.com.valorant.data.repository.ValorantRepository
import kotlinx.coroutines.launch

class MapsViewModel(private val mapsRepository: ValorantRepository) : ViewModel() {
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