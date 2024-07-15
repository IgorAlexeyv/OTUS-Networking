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

    class Impl @Inject constructor(private val okHttpClient: OkHttpClient) : GetProfile {
        override suspend fun invoke(): Profile = withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url("https://my-json-server.typicode.com/Android-Developer-Basic/Networking/profile")
                .get()
                .build()

            okHttpClient.newCall(request).execute().let { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                val body = response.body ?: throw IOException("Empty body $response")
                Json.decodeFromString(Profile.serializer(), body.string())
            }
        }
    }
}