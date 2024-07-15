package otus.gbp.networking.net

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import otus.gbp.networking.data.Profile
import java.io.IOException
import javax.inject.Inject

interface GetProfile {
    suspend operator fun invoke(): Profile

    class Impl @Inject constructor(private val api: Api) : GetProfile {
        override suspend fun invoke(): Profile {
            val response = api.getProfile()
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            }
            return response.body() ?: throw IOException("Empty body $response")
        }
    }
}