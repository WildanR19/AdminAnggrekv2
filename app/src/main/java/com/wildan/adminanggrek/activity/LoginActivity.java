package com.wildan.adminanggrek.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.wildan.adminanggrek.R;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout textInputLayoutUsername, textInputLayoutPassword;
    EditText edittext_username, edittext_password;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginMasuk(View view) {
        //Method onClick pada Button

        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        edittext_username = findViewById(R.id.editTextUsername);
        edittext_password = (EditText) findViewById(R.id.editTextPassword);
        username = edittext_username.getText().toString();
        password = edittext_password.getText().toString();

        //Kondisi jika username dan password benar maka akan menampilkan pesan text toast
        //Login sukses dan masuk ke activity 2
        if ((username.contains("admin"))&&((password.contains("admin")))) {
            Toast.makeText(this, "Login Sukses", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        else if ((username.matches("")||password.matches("")))
          /*
          Atau jika input text 1 dan text 2 kosong
           */
        {
            //Maka akan menampilkan pesan text toast
            Toast.makeText(this, "Isikan Username dan Password", Toast.LENGTH_SHORT).show();

        }

        else {
            //jika kedua kondisi diatas tidak memenuhi

            Toast.makeText(this, "Login Gagal /Username Password Salah", Toast.LENGTH_SHORT).show();
        }

    }
}
