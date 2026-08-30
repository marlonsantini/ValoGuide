package fingerfire.com.valoguide.features.weapons.ui.viewstate

import fingerfire.com.valoguide.features.weapons.data.response.WeaponDataResponse
import fingerfire.com.valoguide.features.weapons.data.response.WeaponResponse

data class WeaponViewState(
    val sucess: WeaponResponse? = null,
    val failure: Boolean = false,
    val searchQuery: String = ""
) {
    val filteredWeapons: List<WeaponDataResponse>
        get() {
            val weapons = sucess?.weapons.orEmpty()
            val query = searchQuery
            return if (query.isBlank()) {
                weapons
            } else {
                weapons.filter { it.displayName.contains(query, ignoreCase = true) }
            }
        }
}
