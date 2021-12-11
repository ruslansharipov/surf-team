package ru.surfstudio.surf_team.i_team

import retrofit2.http.GET
import ru.surfstudio.surf_team.i_team.response.TeamUnitResponse

interface TeamApi {

    @GET("/projects-with-employees")
    suspend fun getSurfTeam() : List<TeamUnitResponse>

    companion object {
        const val BASE_URL = "http://demo4547913.mockable.io"
    }
}