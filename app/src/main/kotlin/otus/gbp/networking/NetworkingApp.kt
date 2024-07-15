package otus.gbp.networking

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@HiltAndroidApp
class NetworkingApp : Application()

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun sessionManager(impl: SessionManager.Impl): SessionManager
}
