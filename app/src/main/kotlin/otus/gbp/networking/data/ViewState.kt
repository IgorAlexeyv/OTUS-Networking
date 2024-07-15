package otus.gbp.networking.data

sealed class ViewState {
    data object None : ViewState()
    data object Loading : ViewState()
    data class Content(val profile: Profile) : ViewState()
}