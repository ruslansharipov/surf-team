package ru.surfstudio.surf_team.domain

data class TeamUnit(
    val descriptions: String = "",
    val id: String = "",
    val name: String = "",
    val employees: List<Employee> = listOf()
)