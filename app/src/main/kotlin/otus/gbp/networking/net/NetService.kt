package otus.gbp.networking.net

import kotlinx.coroutines.delay
import kotlinx.datetime.Instant
import otus.gbp.networking.data.Profile
import javax.inject.Inject

interface NetService {
    suspend fun getProfile(): Profile

    class Impl @Inject constructor() : NetService {
        override suspend fun getProfile(): Profile {
            delay(1000)
            return Profile(
                userId = 1,
                name = "Vasya",
                age = 25,
                registered = Instant.parse("2023-11-17T11:43:22.306Z"),
                interests = listOf("рыбалка", "корутины", "футбол")
            )
        }
    }
}

