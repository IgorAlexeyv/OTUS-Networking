package otus.gbp.networking.net

import okhttp3.Interceptor
import okhttp3.Response
import otus.gbp.networking.SessionManager
import javax.inject.Inject
import javax.inject.Named

class AuthInterceptor @Inject constructor(private val sessionManager: SessionManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestWithAuth = request.newBuilder()
            .header("Authorization", "Bearer: ${sessionManager.getToken()}")
            .build()

        return chain.proceed(requestWithAuth)
    }
}