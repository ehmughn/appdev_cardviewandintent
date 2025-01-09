package com.example.cardviewandintent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText email_editText, pass_editText, number_editText, name_editText;
    private Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email_editText = findViewById(R.id.email_editText);
        pass_editText = findViewById(R.id.pass_editText);
        number_editText = findViewById(R.id.number_editText);
        name_editText = findViewById(R.id.name_editText);
        submit_btn = findViewById(R.id.submit_btn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidInput()) {
                    // Proceed with login or other actions
                    Toast.makeText(MainActivity.this, "Valid inputs!", Toast.LENGTH_SHORT).show();
                } else {
                    // Show an error message if validation fails
                    Toast.makeText(MainActivity.this, "Invalid inputs, please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidInput() {
        // Get text from the EditText fields
        String email = email_editText.getText().toString();
        String password = pass_editText.getText().toString();
        String number = number_editText.getText().toString();
        String name = name_editText.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_editText.setError("Email is required");
            return false;
        }

        if (password.isEmpty()) {
            pass_editText.setError("Password is required");
            return false;
        } else if (password.length() < 6) {
            pass_editText.setError("Password must be at least 6 characters");
            return false;
        }

        if (number.length() != 11) {
            number_editText.setError("Contact number must be 11 digits");
            return false;
        }

        if (name.isEmpty()) {
            name_editText.setError("Enter your Full Name");
            return false;
        }

        return true;
    }
}