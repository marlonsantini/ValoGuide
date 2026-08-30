package fingerfire.com.valoguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fingerfire.com.valoguide.ui.screens.agents.AgentDetailScreen
import fingerfire.com.valoguide.ui.screens.agents.AgentsScreen
import fingerfire.com.valoguide.ui.screens.maps.MapDetailScreen
import fingerfire.com.valoguide.ui.screens.maps.MapsScreen
import fingerfire.com.valoguide.ui.screens.weapons.WeaponDetailScreen
import fingerfire.com.valoguide.ui.screens.weapons.WeaponsScreen
import fingerfire.com.valoguide.ui.theme.ValoGuideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ValoGuideTheme {
                ValoGuideApp()
            }
        }
    }
}

@Composable
fun ValoGuideApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val showBottomBar = currentRoute in listOf(
        Screen.Agents.route,
        Screen.Maps.route,
        Screen.Weapons.route
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    listOf(Screen.Agents, Screen.Maps, Screen.Weapons).forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(screen.iconRes),
                                    contentDescription = stringResource(screen.titleRes)
                                )
                            },
                            label = { Text(stringResource(screen.titleRes)) },
                            selected = currentDestination?.hierarchy?.any {
                                it.route == screen.route
                            } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Agents.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Agents.route) {
                AgentsScreen(
                    onAgentClick = { uuid ->
                        navController.navigate("agents/$uuid")
                    }
                )
            }
            composable(
                route = "agents/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { entry ->
                val uuid = entry.arguments?.getString("uuid").orEmpty()
                AgentDetailScreen(
                    uuid = uuid,
                    onBack = { navController.popBackStack() }
                )
            }
            composable(Screen.Maps.route) {
                MapsScreen(
                    onMapClick = { uuid ->
                        navController.navigate("maps/$uuid")
                    }
                )
            }
            composable(
                route = "maps/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { entry ->
                val uuid = entry.arguments?.getString("uuid").orEmpty()
                MapDetailScreen(
                    uuid = uuid,
                    onBack = { navController.popBackStack() }
                )
            }
            composable(Screen.Weapons.route) {
                WeaponsScreen(
                    onWeaponClick = { uuid ->
                        navController.navigate("weapons/$uuid")
                    }
                )
            }
            composable(
                route = "weapons/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { entry ->
                val uuid = entry.arguments?.getString("uuid").orEmpty()
                WeaponDetailScreen(
                    uuid = uuid,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

sealed class Screen(val route: String, val titleRes: Int, val iconRes: Int) {
    data object Agents : Screen("agents", R.string.agents, R.drawable.ic_agents)
    data object Maps : Screen("maps", R.string.maps, R.drawable.ic_maps)
    data object Weapons : Screen("weapons", R.string.arsenal, R.drawable.ic_weapons)
}
