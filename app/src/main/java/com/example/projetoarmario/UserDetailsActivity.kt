package com.example.projetoarmario

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoarmario.databinding.ActivityUserDetailsBinding

// Activity para exibir os detalhes do usuário
class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        val email = intent.getStringExtra("USER_EMAIL")
        if (email != null) {
            val cursor = databaseHelper.getUserDetails(email)
            if (cursor.moveToFirst()) {
                // Preenche os campos de texto com os detalhes do usuário
                binding.textViewFirstName.text = cursor.getString(cursor.getColumnIndexOrThrow("first_name"))
                binding.textViewLastName.text = cursor.getString(cursor.getColumnIndexOrThrow("last_name"))
                binding.textViewBirthDate.text = cursor.getString(cursor.getColumnIndexOrThrow("birth_date"))
                binding.textViewCPF.text = cursor.getString(cursor.getColumnIndexOrThrow("cpf"))
                binding.textViewCEP.text = cursor.getString(cursor.getColumnIndexOrThrow("cep"))
                binding.textViewStreet.text = cursor.getString(cursor.getColumnIndexOrThrow("street"))
                binding.textViewCity.text = cursor.getString(cursor.getColumnIndexOrThrow("city"))
                binding.textViewState.text = cursor.getString(cursor.getColumnIndexOrThrow("state"))
                binding.textViewPhone.text = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
                binding.textViewEmail.text = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            }
            cursor.close()
        }
    }
}