package com.example.projetoarmario

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoarmario.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.buttonRegister.setOnClickListener {
            val firstName = binding.editTextFirstName.text.toString()
            val lastName = binding.editTextLastName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val birthDate = binding.editTextBirthDate.text.toString()
            val cpf = binding.editTextCPF.text.toString()
            val cep = binding.editTextCEP.text.toString()
            val street = binding.editTextStreet.text.toString()
            val city = binding.editTextCity.text.toString()
            val state = binding.editTextState.text.toString()
            val phone = binding.editTextPhone.text.toString()

            val id = databaseHelper.addUser(firstName, lastName, email, password, birthDate, cpf, cep, street, city, state, phone)
            if (id > 0) {
                Toast.makeText(this, "Registro bem-sucedido", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro no registro", Toast.LENGTH_SHORT).show()
            }
        }
    }
}