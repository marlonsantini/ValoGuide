package fingerfire.com.valoguide.features.weapons.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valoguide.features.weapons.data.repository.WeaponsRepository
import fingerfire.com.valoguide.features.weapons.ui.viewstate.WeaponViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeaponViewModel(private val weaponsRepository: WeaponsRepository) : ViewModel() {

    private val _weaponsState = MutableStateFlow(WeaponViewState())
    val weaponsState: StateFlow<WeaponViewState> = _weaponsState.asStateFlow()

    fun getWeapons() {
        viewModelScope.launch {
            val weaponResponse = weaponsRepository.loadWeapons()
            if (weaponResponse.isSuccessful && weaponResponse.body() != null) {
                _weaponsState.update {
                    it.copy(sucess = weaponResponse.body(), failure = false)
                }
            } else {
                _weaponsState.update { it.copy(failure = true, sucess = null) }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _weaponsState.update { it.copy(searchQuery = query) }
    }
}
