package otus.gbp.networking

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.Instant
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import otus.gbp.networking.data.Profile
import otus.gbp.networking.data.ViewState
import otus.gbp.networking.net.NetService

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private val profile = Profile(
        userId = 1,
        name = "Vasya",
        age = 25,
        registered = Instant.parse("2023-11-17T11:43:22.306Z"),
        interests = listOf("рыбалка", "корутины", "футбол")
    )
    private lateinit var service: NetService
    private lateinit var model: MainViewModel
    private lateinit var observer: Observer<ViewState>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)

        service = mock {
            onBlocking { this.getProfile() } doReturn profile
        }
        model = MainViewModel(service)
        observer = mock()
        model.uiState.observeForever(observer)
    }

    @After
    fun deinit() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadsProfile() = runTest {
        model.getProfile()
        inOrder(observer) {
            verify(observer).onChanged(ViewState.None)
            verify(observer).onChanged(ViewState.Loading)
            verify(observer).onChanged(ViewState.Content(profile))
        }
    }
}