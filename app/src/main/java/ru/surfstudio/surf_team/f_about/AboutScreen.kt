package ru.surfstudio.surf_team.f_about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.surfstudio.surf_team.R
import ru.surfstudio.surf_team.ui.theme.SurfteamTheme

@Composable
fun AboutScreen() {
    val context = LocalContext.current

    Column {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_android),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.LightGray), // TODO заменить на цвет из темы
                modifier = Modifier
                    .size(56.dp, 32.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.about_subtitle),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 16.dp),
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.waterloo)
            )
            TextButton(
                onClick = {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/ruslansharipov/surf-team")
                        )
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(24.dp)
            ) {
                Text(text = stringResource(id = R.string.about_github))
            }
        }

        Box(contentAlignment = Alignment.BottomCenter) {
            Button(
                onClick = {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://t.me/surf_android")
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = stringResource(id = R.string.about_chat_btn))
            }
        }
    }
}

@Preview
@Composable
fun AboutPreview() {
    SurfteamTheme(darkTheme = true) {
        AboutScreen()
    }
}