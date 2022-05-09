package com.timmy.codelab.sandbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Test

class SerializationTest {

    @Test
    fun test() {
        val string1 = """
            {
              "id": "80387a22-e5de-4e44-99a2-d4f464f63cc9",
              "speakers": [
                {
                  "name": "timmy",
                  "gender": "M"
                },
                {
                  "name": "ymmitt",
                  "gender": "F"
                }
              ],
              "currentMember": true
            }
        """.trimIndent()

        val string2 = """
            {
              "id": "80387a22-e5de-4e44-99a2-d4f464f63cc9",
              "speakers": [
                {
                  "name": "timmy",
                  "gender": "M"
                },
                {
                  "name": "ymmit",
                  "gender": "F"
                }
              ],
              "currentMember": true
            }
        """.trimIndent()

        val response1 = Json.decodeFromString(Message.serializer(), string1)
        val response2 = Json.decodeFromString(Message.serializer(), string2)
        println(response1 == response2)
        println(response1 === response2)
    }

    @Serializable
    data class Message(
        @SerialName("id") val id: String,
        @SerialName("speakers") val speaker: List<Speaker>,
        @SerialName("currentMember") val currentMember: Boolean
    )

    @Serializable
    data class Speaker(
        @SerialName("name") val name: String,
        @SerialName("gender") val gender: String,
    )
}