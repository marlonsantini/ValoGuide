package fingerfire.com.valoguide.features.maps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valoguide.features.maps.data.repository.MapsRepository
import fingerfire.com.valoguide.features.maps.ui.viewstate.MapDetailViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapDetailViewModel(private val mapsRepository: MapsRepository) : ViewModel() {

    private val _mapDetailState = MutableStateFlow(MapDetailViewState())
    val mapDetailState: StateFlow<MapDetailViewState> = _mapDetailState.asStateFlow()

    fun getMapDetail(uuid: String) {
        viewModelScope.launch {
            try {
                val response = mapsRepository.getMapDetail(uuid)
                _mapDetailState.value = MapDetailViewState(sucess = response)
            } catch (_: Exception) {
                _mapDetailState.value = MapDetailViewState(failure = true)
            }
        }
    }
}
