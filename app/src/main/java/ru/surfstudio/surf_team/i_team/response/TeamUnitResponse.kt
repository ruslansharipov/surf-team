package ru.surfstudio.surf_team.i_team.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.surfstudio.surf_team.domain.TeamUnit

@Serializable
data class TeamUnitResponse(
    @SerialName("descriptions")
    val descriptions: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("team")
    val team: TeamResponse = TeamResponse()
) {
    fun toTeamUnit() : TeamUnit {
        return TeamUnit(descriptions, id, name, team.employees.map { it.toEmployee() })
    }
}