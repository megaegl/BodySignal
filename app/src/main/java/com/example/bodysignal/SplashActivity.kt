package com.example.bodysignal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bodysignal.R
import com.example.bodysignal.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splash = object : Thread() {
            override fun run() {
                try {
                    sleep(1000)

                    val intent = Intent(baseContext, MainActivity::class.java)

                    startActivity(intent)
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        splash.start()
    }
}