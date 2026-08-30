package fingerfire.com.valoguide.features.maps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valoguide.features.maps.data.repository.MapsRepository
import fingerfire.com.valoguide.features.maps.ui.viewstate.MapViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapsViewModel(private val mapsRepository: MapsRepository) : ViewModel() {

    private val _mapsState = MutableStateFlow<MapViewState>(MapViewState())
    val mapsState: StateFlow<MapViewState> = _mapsState.asStateFlow()

    fun getMaps() {
        viewModelScope.launch {
            val mapResponse = mapsRepository.loadMaps()
            if (mapResponse.isSuccessful && mapResponse.body() != null) {
                _mapsState.value = MapViewState(sucess = mapResponse.body())
            } else if (mapResponse.errorBody() != null) {
                _mapsState.value = MapViewState(failure = true)
            }
        }
    }
}