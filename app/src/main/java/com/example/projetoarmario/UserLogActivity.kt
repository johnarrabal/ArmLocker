package com.example.projetoarmario

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoarmario.databinding.ActivityUserLogBinding

class UserLogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("USER_NAME")
        binding.welcomeText.text = "Bem vindo, $userName"

        binding.userPhoto.setOnClickListener {
            startActivity(Intent(this, UserDetailsActivity::class.java))
        }
    }
}