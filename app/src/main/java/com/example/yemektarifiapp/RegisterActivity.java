package com.example.yemektarifiapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Lütfen kullanıcı adı ve şifre giriniz", Toast.LENGTH_SHORT).show();
                } else {
                    // Kayıt işlemi (Gerçek uygulamalarda bu işlem sunucu tarafında yapılmalıdır)
                    // Basit bir kontrol ve kayıt işlemi
                    if (saveUser(username, password)) {
                        Toast.makeText(RegisterActivity.this, "Kayıt başarılı", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Kayıt başarısız, tekrar deneyin", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean saveUser(String username, String password) {
        // Basit bir kullanıcı kaydetme işlemi (Örnek için SharedPreferences kullanıyoruz)
        // Gerçek uygulamalarda veritabanı veya sunucu tarafında yapılmalıdır
        getSharedPreferences("UserPrefs", MODE_PRIVATE)
                .edit()
                .putString(username, password)
                .apply();
        return true;
    }
}