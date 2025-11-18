package com.example.wastemanage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnSignup;
    FirebaseAuth mAuth;
    @Override protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_signup);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignup = findViewById(R.id.btnSignup);
        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            if (email.isEmpty() || pass.isEmpty()) { Toast.makeText(this,"Enter details",Toast.LENGTH_SHORT).show(); return; }
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) { finish(); }
                        else Toast.makeText(this, "Signup failed: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    });
        });
    }
}
