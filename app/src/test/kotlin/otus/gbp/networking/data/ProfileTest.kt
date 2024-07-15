package otus.gbp.networking.data

import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class ProfileTest {

    private val profile = Profile(
        userId = 1,
        name = "Vasya",
        age = 25,
        registered = Instant.parse("2023-11-17T11:43:22.306Z"),
        interests = listOf("рыбалка", "корутины", "футбол")
    )

    @Test
    fun serializesProfile() {
        assertEquals(
            """{"id":1,"name":"Vasya","age":25,"registered":"2023-11-17T11:43:22.306Z","interests":["рыбалка","корутины","футбол"]}""",
            Json.encodeToString(Profile.serializer(), profile)
        )
    }

    @Test
    fun deserializesProfile() {
        assertEquals(
            profile,
            Json.decodeFromString("""{"id":1,"name":"Vasya","age":25,"registered":"2023-11-17T11:43:22.306Z","interests":["рыбалка","корутины","футбол"]}""")
        )
    }
}