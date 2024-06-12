package com.example.bodysignal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import com.example.bodysignal.R

class MainMenuActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val d1 = findViewById<CardView>(R.id.d1)
        d1.setOnClickListener {
            val intent = Intent(this, TentangActivity::class.java)
            startActivity(intent)
        }

        val d2 = findViewById<CardView>(R.id.d2)
        d2.setOnClickListener {
            val intent = Intent(this, DatasetActivity::class.java)
            startActivity(intent)
        }

        val d3 = findViewById<CardView>(R.id.d3)
        d3.setOnClickListener {
            val intent = Intent(this, FiturActivity::class.java)
            startActivity(intent)
        }

        val d4 = findViewById<CardView>(R.id.d4)
        d4.setOnClickListener {
            val intent = Intent(this, ModelActivity::class.java)
            startActivity(intent)
        }

        val d5 = findViewById<CardView>(R.id.d5)
        d5.setOnClickListener {
            val intent = Intent(this, SimodelActivity::class.java)
            startActivity(intent)
        }

        back = findViewById(R.id.back)
        back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.back -> run {
                    val back = Intent(this@MainMenuActivity, MainActivity::class.java)
                    startActivity(back)
                }

            }

        }

    }

}

