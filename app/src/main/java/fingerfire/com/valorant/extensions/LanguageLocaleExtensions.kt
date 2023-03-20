package fingerfire.com.valorant.extensions

import java.util.*

fun getAppLanguage(): String {
    return Locale.getDefault().toLanguageTag()
}