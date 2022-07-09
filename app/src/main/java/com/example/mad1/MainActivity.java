package com.example.mad1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    TextView register;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         register = findViewById(R.id.register);
         email = findViewById(R.id.email);
         password = findViewById(R.id.password);
         login = findViewById(R.id.login);
         auth = FirebaseAuth.getInstance();


    register.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this,Register.class);
            startActivity(intent);
        }
    });
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            login();
        }
    });
}

    private void login() {
        String email1 = email.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        if(email1.isEmpty())
        {
            email.setError("Email cannot be empty");
        }
        if(password1.isEmpty()){
            email.setError("Email cannot be empty");
        }
        else
        {
            auth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, home.class));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Login UnSuccessful" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}
