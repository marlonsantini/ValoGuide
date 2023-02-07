package fingerfire.com.valorant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fingerfire.com.valorant.view.ui.weapons.WeaponFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeaponFragment.newInstance())
                .commitNow()
        }
    }
}