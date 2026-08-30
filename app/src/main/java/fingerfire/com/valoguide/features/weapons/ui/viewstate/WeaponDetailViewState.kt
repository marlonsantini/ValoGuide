package fingerfire.com.valoguide.features.weapons.ui.viewstate

import fingerfire.com.valoguide.features.weapons.data.response.DamageRangesResponse
import fingerfire.com.valoguide.features.weapons.data.response.SkinsResponse
import fingerfire.com.valoguide.features.weapons.data.response.WeaponDetailResponse

data class WeaponDetailViewState(
    val sucess: WeaponDetailResponse? = null,
    val failure: Boolean = false
) {
    val isMelee: Boolean
        get() = sucess?.data?.uuid == MELEE_UUID

    val visibleSkins: List<SkinsResponse>
        get() = sucess?.data?.skins.orEmpty().filter {
            !it.displayIcon.isNullOrEmpty() && !it.contentTierUuid.isNullOrEmpty()
        }

    val damageRanges: List<DamageRangesResponse>
        get() = sucess?.data?.weaponStats?.damageRanges.orEmpty()

    companion object {
        const val MELEE_UUID = "2f59173c-4bed-b6c3-2191-dea9b58be9c7"
    }
}
