package fingerfire.com.valorant.data.response

data class VoiceLineResponse(
    val maxDuration: Double,
    val mediaResponseList: List<MediaResponse>,
    val minDuration: Double
)