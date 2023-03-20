package fingerfire.com.valorant.features.weapons.ui.viewstate

import fingerfire.com.valorant.features.weapons.data.response.WeaponResponse

data class WeaponViewState(val sucess: WeaponResponse? = null, val failure: Boolean = false)