package com.example.yemektarifiapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText birthDateEditText;
    private RadioGroup genderRadioGroup;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        birthDateEditText = findViewById(R.id.birthDateEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        registerButton = findViewById(R.id.registerButton);

        // Doğum tarihi seçim işlemi
        birthDateEditText.setOnClickListener(v -> showDatePickerDialog());

        registerButton.setOnClickListener(v -> registerUser());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                    birthDateEditText.setText(selectedDate);
                },
                year, month, day);

        datePickerDialog.show();
    }

    private void registerUser() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String birthDate = birthDateEditText.getText().toString().trim();

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        String gender = "";

        if (selectedGenderId != -1) {
            RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
            gender = selectedGenderRadioButton.getText().toString();
        } else {
            Toast.makeText(this, "Lütfen cinsiyet seçiniz!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(username) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(birthDate) || TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Lütfen tüm alanları doldurunuz", Toast.LENGTH_SHORT).show();
            return;
        }

        if (saveUser(firstName, lastName, username, password, birthDate, gender)) {
            Toast.makeText(this, "Kayıt başarılı", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Kayıt başarısız, tekrar deneyin", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean saveUser(String firstName, String lastName, String username, String password, String birthDate, String gender) {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(username + "_password", password);
        editor.putString(username + "_firstName", firstName);
        editor.putString(username + "_lastName", lastName);
        editor.putString(username + "_birthDate", birthDate);
        editor.putString(username + "_gender", gender);
        editor.apply(); // Daha hızlı ve önerilen yöntem

        return true;
    }
}

