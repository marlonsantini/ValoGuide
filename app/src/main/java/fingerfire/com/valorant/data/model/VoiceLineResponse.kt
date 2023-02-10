package fingerfire.com.valorant.data.model

data class VoiceLineResponse(
    val maxDuration: Double,
    val mediaResponseList: List<MediaResponse>,
    val minDuration: Double
)