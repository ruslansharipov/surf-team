package ru.surfstudio.surf_team

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import ru.surfstudio.surf_team.f_main.MainScreen
import ru.surfstudio.surf_team.f_splash.SplashScreen
import ru.surfstudio.surf_team.navigation.Main
import ru.surfstudio.surf_team.navigation.Splash
import ru.surfstudio.surf_team.ui.theme.SurfteamTheme

class MainActivity : ComponentActivity() {
    @ExperimentalUnitApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SurfteamTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Splash) {
                    composable(Splash) {
                        SplashScreen {
                            navController.popBackStack()
                            navController.navigate(Main)
                        }
                    }
                    composable(Main) { MainScreen() }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SurfteamTheme {
        Greeting("Android")
    }
}