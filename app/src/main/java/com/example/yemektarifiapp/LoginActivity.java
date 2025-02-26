package com.example.yemektarifiapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Lütfen kullanıcı adı ve şifre giriniz", Toast.LENGTH_SHORT).show();
                } else {
                    // Basit bir kontrol (Gerçek uygulamalarda bu kontrol sunucu tarafında yapılmalıdır)
                    if (checkUser(username, password)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Geçersiz kullanıcı adı veya şifre", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkUser(String username, String password) {
        // Basit bir kullanıcı kontrolü (Örnek için SharedPreferences kullanıyoruz)
        // Gerçek uygulamalarda veritabanı veya sunucu tarafında yapılmalıdır
        String savedPassword = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                .getString(username + "_password", null);
        return password.equals(savedPassword);
    }
}