package com.cheng.hellodemo.adapter

import kotlinx.serialization.Serializable
import org.junit.Assert
import org.junit.Test

class JsonHelperTest {

    @Test
    fun `Given an empty json String Then fromJsonString returns null`() {
        val jsonString = ""
        val user = JsonHelper.fromJsonString<User>(jsonString)

        Assert.assertNull(user)
    }

    @Test
    fun `Given a json String of User Then fromJsonString assigns the default value for missing properties`() {
        val jsonString = """
            {
                "name" : "John Doe",
                "email" : "john.doe@email.com"
            }
        """.trimIndent()
        val user = JsonHelper.fromJsonString<User>(jsonString)!!

        Assert.assertEquals("John Doe", user.name)
        Assert.assertEquals("john.doe@email.com", user.email)
        Assert.assertEquals(13, user.age)
        Assert.assertEquals(Role.Editor, user.role)
    }

    @Serializable
    private data class User(
        val name: String,
        val email: String,
        val age: Int = 13,
        val role: Role = Role.Editor
    )

    private enum class Role { Viewer, Editor, Owner }

}
