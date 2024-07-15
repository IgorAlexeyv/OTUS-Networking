package otus.gbp.networking.net

import otus.gbp.networking.data.Profile

interface GetProfile {
    suspend operator fun invoke(): Profile
}