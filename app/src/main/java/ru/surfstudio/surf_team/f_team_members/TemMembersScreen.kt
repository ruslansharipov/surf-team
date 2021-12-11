package ru.surfstudio.surf_team.f_team_members

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.flowlayout.FlowRow
import ru.surfstudio.surf_team.R
import ru.surfstudio.surf_team.domain.Employee
import ru.surfstudio.surf_team.domain.UserInfo
import ru.surfstudio.surf_team.ui.theme.SurfteamTheme

@ExperimentalMaterialApi
@Composable
fun TeamMembersScreen(members: List<Employee>) {
    LazyColumn(
        content = {
            items(members) {
                TeamMember(employee = it)
            }
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun TeamMember(employee: Employee) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(12.dp),
        onClick = {  }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = employee.userInfo.name, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = employee.photoUrl, builder = {
                        transformations(CircleCropTransformation())
                    }),
                    contentDescription = null,
                    modifier = Modifier.size(34.dp, 34.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = employee.position, style = MaterialTheme.typography.body2)
                    FlowRow(
                        mainAxisSpacing = 4.dp,
                        crossAxisSpacing = 4.dp,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        employee.skills.forEachIndexed { index, skill ->
                            Text(
                                text = skill,
                                modifier = Modifier
                                    .background(
                                        color = if (index == 0) {
                                            MaterialTheme.colors.secondary
                                        } else {
                                            colorResource(id = R.color.waterloo_12)
                                        },
                                        shape = RoundedCornerShape(CornerSize(12.dp))
                                    )
                                    .padding(vertical = 2.dp, horizontal = 8.dp),
                                style = MaterialTheme.typography.caption,
                                color = colorResource(id = R.color.waterloo)
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun TeamMemberPreview() {
    SurfteamTheme(darkTheme = true) {
        TeamMembersScreen(
            members = listOf(
                Employee(
                    userInfo = UserInfo(name = "Тест"),
                    position = "test position",
                    skills = listOf("1", "2", "3")
                ),
                Employee(
                    userInfo = UserInfo(name = "Тест"),
                    position = "test position",
                    skills = listOf("1", "2", "3")
                )
            )
        )
    }
}