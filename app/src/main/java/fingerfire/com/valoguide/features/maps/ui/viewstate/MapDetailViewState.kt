package fingerfire.com.valoguide.features.maps.ui.viewstate

import fingerfire.com.valoguide.features.maps.data.response.MapDetailResponse

data class MapDetailViewState(
    val sucess: MapDetailResponse? = null,
    val failure: Boolean = false
)
