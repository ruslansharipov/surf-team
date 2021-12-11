package ru.surfstudio.surf_team.i_team.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.surfstudio.surf_team.domain.Employee

@Serializable
data class EmployeeResponse(
    @SerialName("createdDate")
    val createdDate: Int = 0,
    @SerialName("currentProject")
    val currentProject: String = "",
    @SerialName("department")
    val department: String = "",
    @SerialName("experience")
    val experience: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("photoUrl")
    val photoUrl: String = "",
    @SerialName("position")
    val position: String = "",
    @SerialName("skills")
    val skills: List<String> = listOf(),
    @SerialName("userInfo")
    val userInfo: UserInfoResponse = UserInfoResponse()
) {
    fun toEmployee() : Employee {
        return Employee(createdDate, currentProject, department, experience, id, position, skills, userInfo.toUserInfo())
    }
}