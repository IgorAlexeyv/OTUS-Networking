package otus.gbp.networking

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import otus.gbp.networking.data.Profile
import otus.gbp.networking.data.ViewState
import otus.gbp.networking.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.uiState.observe(this) { state ->
            when(state) {
                ViewState.None -> toNone()
                ViewState.Loading -> toLoading()
                is ViewState.Content -> toContent(state.profile)
            }
        }

        binding.button.setOnClickListener {
            viewModel.getProfile()
        }
    }

    private fun toNone() = with(binding) {
        nameLabel.isVisible = false
        name.isVisible = false
        ageLabel.isVisible = false
        age.isVisible = false
        progressBar.isVisible = false
    }

    private fun toLoading() = with(binding) {
        nameLabel.isVisible = false
        name.isVisible = false
        ageLabel.isVisible = false
        age.isVisible = false
        progressBar.isVisible = true
    }

    private fun toContent(profile: Profile) = with(binding) {
        nameLabel.isVisible = true
        name.isVisible = true
        name.text = profile.name
        ageLabel.isVisible = true
        age.isVisible = true
        age.text = profile.age.toString()
        progressBar.isVisible = false
    }
}