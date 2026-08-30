package fingerfire.com.valoguide.features.weapons.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valoguide.features.weapons.data.repository.WeaponsRepository
import fingerfire.com.valoguide.features.weapons.ui.viewstate.WeaponDetailViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeaponDetailViewModel(private val weaponsRepository: WeaponsRepository) : ViewModel() {

    private val _weaponDetailState = MutableStateFlow(WeaponDetailViewState())
    val weaponDetailState: StateFlow<WeaponDetailViewState> = _weaponDetailState.asStateFlow()

    fun getWeaponDetail(uuid: String) {
        viewModelScope.launch {
            try {
                val response = weaponsRepository.getWeaponDetail(uuid)
                _weaponDetailState.value = WeaponDetailViewState(sucess = response)
            } catch (_: Exception) {
                _weaponDetailState.value = WeaponDetailViewState(failure = true)
            }
        }
    }
}
