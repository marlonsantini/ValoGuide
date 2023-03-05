package fingerfire.com.valorant.util

import java.util.*

class Util {

    companion object {
        val setAppLanguage: String
            get() {
                return Locale.getDefault().toLanguageTag()
            }
    }
}