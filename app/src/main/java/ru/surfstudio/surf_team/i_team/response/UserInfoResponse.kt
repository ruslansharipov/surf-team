package ru.surfstudio.surf_team.i_team.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.surfstudio.surf_team.domain.UserInfo

@Serializable
data class UserInfoResponse(
    @SerialName("age")
    val age: Int = 0,
    @SerialName("city")
    val city: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("phone")
    val phone: String = ""
) {
    fun toUserInfo() : UserInfo {
        return UserInfo(age, city, name, phone)
    }
}