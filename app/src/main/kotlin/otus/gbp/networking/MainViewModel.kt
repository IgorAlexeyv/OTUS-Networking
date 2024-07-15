package otus.gbp.networking

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import otus.gbp.networking.data.ViewState
import otus.gbp.networking.net.AuthInterceptor
import otus.gbp.networking.net.GetProfile
import otus.gbp.networking.net.NetService
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(private val service: NetService) : ViewModel() {
    private val mUiState = MutableLiveData<ViewState>(ViewState.None)
    val uiState: LiveData<ViewState> get() = mUiState

    fun getProfile() {
        viewModelScope.launch {
            mUiState.value = ViewState.Loading
            mUiState.value = ViewState.Content(service.getProfile())
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainModule {
    @Binds
    abstract fun netService(impl: NetService.Impl) : NetService

    @Binds
    abstract fun getProfile(impl: GetProfile.Impl) : GetProfile
}

@Module
@InstallIn(ViewModelComponent::class)
class MainModuleProvider {
    @Provides
    fun okHttp(authInterceptor: AuthInterceptor): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        })
        .build()
}