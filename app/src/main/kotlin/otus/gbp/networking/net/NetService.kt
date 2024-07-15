package otus.gbp.networking.net

import kotlinx.coroutines.delay
import kotlinx.datetime.Instant
import otus.gbp.networking.data.Profile
import javax.inject.Inject

interface NetService {
    suspend fun getProfile(): Profile
    suspend fun setProfile(profile: Profile): Profile

    class Impl @Inject constructor(
        private val profileCommand: GetProfile,
        private val setProfileCommand: SetProfile
    ) : NetService {
        override suspend fun getProfile(): Profile = profileCommand()
        override suspend fun setProfile(profile: Profile): Profile = setProfileCommand(profile)
    }
}

