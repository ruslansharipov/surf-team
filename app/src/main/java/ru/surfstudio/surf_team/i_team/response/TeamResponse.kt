package ru.surfstudio.surf_team.i_team.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.surfstudio.surf_team.domain.Team

@Serializable
data class TeamResponse(
    @SerialName("employees")
    val employees: List<EmployeeResponse> = listOf()
)