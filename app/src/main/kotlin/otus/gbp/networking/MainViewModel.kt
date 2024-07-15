package otus.gbp.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import otus.gbp.networking.data.ViewState
import otus.gbp.networking.net.NetService
import javax.inject.Inject

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
}