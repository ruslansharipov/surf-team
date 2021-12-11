package ru.surfstudio.surf_team.f_team_members

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.surfstudio.surf_team.domain.Employee
import ru.surfstudio.surf_team.domain.UserInfo
import ru.surfstudio.surf_team.ui.theme.SurfteamTheme

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

@Composable
fun TeamMember(employee: Employee) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = employee.userInfo.name)
        Row(
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .size(34.dp, 34.dp)
            )
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(text = employee.position)
                Text(text = employee.skills.joinToString())
            }
        }
    }
}

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