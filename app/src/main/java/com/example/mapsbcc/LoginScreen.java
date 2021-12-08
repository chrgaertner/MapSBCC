package com.example.mapsbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    private Button button;
    private EditText eUsername;
    private EditText ePassword;

    private final String verifiedUsername = "Admin";
    private final String verifiedPassword = "1234";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        sharedPreferences = getApplicationContext().getSharedPreferences("Database", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        eUsername = findViewById(R.id.bUsername);
        ePassword = findViewById(R.id.bPassword);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = eUsername.getText().toString();
                String inputPassword =ePassword.getText().toString();

                if(validateInput(inputUsername,inputPassword)){
                    Toast.makeText(LoginScreen.this, "Login was succesful", Toast.LENGTH_LONG).show();
                    openMenu();
                } else {
                    Toast.makeText(LoginScreen.this, "Wrong Username/Password!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    // Need to change to equals later, we ignorecase for test purposes.
    // If the username input equals the prefixed username and login we will return true, if not false.
    private boolean validateInput(String username, String password){

    if(username.equalsIgnoreCase(verifiedUsername) && password.equalsIgnoreCase(verifiedPassword)){
        return true;
    }
    return false;
    }

    public void openMenu() {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }
}