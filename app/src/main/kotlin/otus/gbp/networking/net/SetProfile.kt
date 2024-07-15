package otus.gbp.networking.net

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import otus.gbp.networking.data.Profile
import java.io.IOException
import javax.inject.Inject

interface SetProfile {
    suspend operator fun invoke(profile: Profile): Profile

    class Impl @Inject constructor(private val api: Api) : SetProfile {
        override suspend fun invoke(profile: Profile): Profile {
            val response = api.setProfile(profile)
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            }
            return response.body() ?: throw IOException("Empty body $response")
        }
    }
}