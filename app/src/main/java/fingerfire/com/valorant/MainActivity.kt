package fingerfire.com.valorant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import fingerfire.com.valorant.databinding.ActivityMainBinding
import fingerfire.com.valorant.view.ui.agents.AgentFragment
import fingerfire.com.valorant.view.ui.maps.MapsFragment
import fingerfire.com.valorant.view.ui.weapons.WeaponFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(AgentFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_item_1 -> replaceFragment(AgentFragment())
                R.id.nav_item_2 -> replaceFragment(WeaponFragment())
                R.id.nav_item_3 -> replaceFragment(MapsFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commitNow()
    }
}