package ru.surfstudio.surf_team.i_team

import ru.surfstudio.surf_team.domain.TeamUnit

class TeamInteractor(
    private val teamApi: TeamApi
) {

    suspend fun getTeamData() : List<TeamUnit> {
        val teamResponse = teamApi.getSurfTeam()
        return teamResponse.map { it.toTeamUnit() }
    }
}