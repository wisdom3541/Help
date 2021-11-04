package com.example.help

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.help.databinding.SplashscreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding : SplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = SplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //timer to home page
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //do  nothing
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashScreen,home::class.java))
                finish()
            }
        }.start()
    }
}