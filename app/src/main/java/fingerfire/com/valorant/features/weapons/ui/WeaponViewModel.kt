package fingerfire.com.valorant.features.weapons.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.features.weapons.data.repository.WeaponsRepository
import fingerfire.com.valorant.features.weapons.ui.viewstate.WeaponViewState
import kotlinx.coroutines.launch

class WeaponViewModel(private val weaponsRepository: WeaponsRepository) : ViewModel() {

    private val weaponsMutableLiveData: MutableLiveData<WeaponViewState> =
        MutableLiveData<WeaponViewState>()
    val weaponsLiveData: LiveData<WeaponViewState>
        get() {
            return weaponsMutableLiveData
        }

    fun getWeapons() {
        viewModelScope.launch {
            val weaponResponse = weaponsRepository.loadWeapons()
            if (weaponResponse.isSuccessful && weaponResponse.body() != null) {
                weaponsMutableLiveData.postValue(WeaponViewState(sucess = weaponResponse.body()))
            } else if (weaponResponse.errorBody() != null) {
                weaponsMutableLiveData.postValue(WeaponViewState(failure = true))
            }
        }
    }
}