package fingerfire.com.valorant.features.maps.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.features.maps.data.repository.MapsRepository
import fingerfire.com.valorant.features.maps.ui.viewstate.MapViewState
import kotlinx.coroutines.launch

class MapsViewModel(private val mapsRepository: MapsRepository) : ViewModel() {

    private val mapsMutableLiveData: MutableLiveData<MapViewState> =
        MutableLiveData<MapViewState>()
    val mapsLiveData: LiveData<MapViewState>
        get() {
            return mapsMutableLiveData
        }

    fun getMaps() {
        viewModelScope.launch {
            val mapResponse = mapsRepository.loadMaps()
            if (mapResponse.isSuccessful && mapResponse.body() != null) {
                mapsMutableLiveData.postValue(MapViewState(sucess = mapResponse.body()))
            } else if (mapResponse.errorBody() != null) {
                mapsMutableLiveData.postValue(MapViewState(failure = true))
            }
        }
    }
}