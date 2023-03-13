package fingerfire.com.valorant.features.maps.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.features.maps.data.repository.MapsRepository
import fingerfire.com.valorant.features.maps.data.response.MapResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MapsViewModel(private val mapsRepository: MapsRepository) : ViewModel() {
    private val mapsMutableLiveData: MutableLiveData<Response<MapResponse>> = MutableLiveData<Response<MapResponse>>()
    val mapsLiveData: LiveData<Response<MapResponse>>
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