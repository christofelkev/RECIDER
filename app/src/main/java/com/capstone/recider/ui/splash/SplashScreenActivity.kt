package com.capstone.recider.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.recider.R
import com.capstone.recider.databinding.ActivitySplashScreenBinding
import com.capstone.recider.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.alpha = 0f
        binding.imageView.animate().setDuration(ANIMATION_DURATION.toLong()).alpha(1f)
            .withEndAction {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()
            }

    }

    companion object {
        private const val ANIMATION_DURATION = 2500
    }

}