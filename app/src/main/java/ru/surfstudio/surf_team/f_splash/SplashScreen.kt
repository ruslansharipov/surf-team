package ru.surfstudio.surf_team.f_splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import ru.surfstudio.surf_team.R
import ru.surfstudio.surf_team.navigation.Main
import ru.surfstudio.surf_team.ui.theme.SurfteamTheme

@Composable
fun SplashScreen(navigationListener: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(top = 72.dp, bottom = 24.dp)
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(
                id = if (isSystemInDarkTheme()) {
                    R.drawable.ic_splash_logo_dark
                } else {
                    R.drawable.ic_splash_logo_light
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_android),
            colorFilter = ColorFilter.tint(
                colorResource(id = R.color.cornflower_blue)
            ),
            contentDescription = null,
            modifier = Modifier
                .size(width = 56.dp, height = 32.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
    LaunchedEffect(key1 = true, block = {
        delay(500)
        navigationListener()
    })
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    SurfteamTheme(darkTheme = true) {
        SplashScreen({})
    }
}
