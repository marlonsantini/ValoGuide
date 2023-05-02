package fingerfire.com.valorant.extensions

import java.util.*

fun getAppLanguage(): String {
    var locale = Locale.getDefault().toLanguageTag()

    val validLocales = listOf(
        "ar-AE", "de-DE", "en-US", "es-ES", "es-MX", "fr-FR", "id-ID", "it-IT",
        "ja-JP", "ko-KR", "pl-PL", "pt-BR", "ru-RU", "th-TH", "tr-TR", "vi-VN", "zh-CN", "zh-TW"
    )

    if(locale.startsWith("es")) {
        locale = "es-ES"
    } else if (locale.startsWith("en")) {
        locale = "en-US"
    } else if (locale.startsWith("de")) {
        locale = "de-DE"
    } else if (locale.startsWith("fr")) {
        locale = "fr-FR"
    } else if (locale.startsWith("zh")) {
        locale = "zh-CN"
    } else if (locale !in validLocales){
        locale = "en-US"
    }

    return locale
}