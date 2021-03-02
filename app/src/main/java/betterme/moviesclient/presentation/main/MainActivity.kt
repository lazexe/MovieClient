package betterme.moviesclient.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import betterme.moviesclient.MovieApplication
import betterme.moviesclient.databinding.ActivityMainBinding
import betterme.moviesclient.presentation.resources.ResourcesProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var resourceProvider: ResourcesProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MovieApplication).appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MainPagerAdapter(resourceProvider, supportFragmentManager)
        binding.mainViewPager.adapter = adapter

        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
    }
}