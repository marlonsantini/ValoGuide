package fingerfire.com.valorant

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import fingerfire.com.valorant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()

    }

    private fun initNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        onDestinationChanged(navController)
    }

    private fun onDestinationChanged(navController: NavController){
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.agentFragment -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
                R.id.mapFragment -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
                R.id.weaponFragment -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}