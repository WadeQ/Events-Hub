package com.wadektech.eventshub.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.wadektech.eventshub.R;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText inputEmail;
    private Button btnReset;
    private TextView btnBack;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private ImageView backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        inputEmail = findViewById(R.id.email);
        btnReset = findViewById(R.id.btn_reset_password);
        btnBack = findViewById(R.id.link_login);
        progressBar = findViewById(R.id.progressBar);
        backbtn= findViewById(R.id.backbtn);

        backbtn.setOnClickListener(v -> finish());

        auth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(v -> finish());

        btnReset.setOnClickListener(v -> {

            String email = inputEmail.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            auth.sendPasswordResetEmail(email)

                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    });
        });
    }
}
