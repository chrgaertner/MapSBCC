package com.example.mapsbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    // Create button
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Assign ID to button
        button = (Button) findViewById(R.id.openGPS);
        // Set onclickListener to execute openGPS() when clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGPS();
            }
        });
        // Assign ID to button
        button = (Button) findViewById(R.id.openRoute);
        // Set onclickListener to execute openRoutes() when clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRoutes();
            }
        });

        // Assign ID to button
        button = (Button) findViewById(R.id.logout);
        // Set onclickListener to execute logOut() when clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    // Open MainActivity when executed
    public void openGPS() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Open Routes when executed
    public void openRoutes() {
        Intent intent = new Intent(this, Routes.class);
        startActivity(intent);
    }

    // Go back to LoginScreen when executed
    public void logOut() {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }
}