package com.example.mapsbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginScreen extends AppCompatActivity {
    // Create button for opening another activity
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // Assign textview ID to thge button
        button = (Button) findViewById(R.id.openGPS);
        // Create a OnClickListener when we click the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            //
            public void onClick(View view) {
                //Activate openMenu function
                openMenu();
            }
        });
    }

    // Function which will start the Menu.class
    public void openMenu() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}