package otus.gbp.networking.net

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import otus.gbp.networking.data.Profile
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val baseUrl = "https://my-json-server.typicode.com/Android-Developer-Basic/Networking/"

interface Api {
    @GET("profile")
    suspend fun getProfile(@Query("id") id: Int): Response<Profile>
}

fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()
