package otus.gbp.networking.data

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    @SerialName("id")
    val userId: Int,
    val name: String,
    val age: Int,
    val registered: Instant,
    val interests: List<String> = emptyList()
)