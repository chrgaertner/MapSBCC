package com.example.mapsbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGPS();
            }
        });

        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRoutes();
            }
        });

        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }
    public void openGPS() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openRoutes() {
        Intent intent = new Intent(this,Routes.class);
        startActivity(intent);
    }

    public void logOut() {
        Intent intent = new Intent(this,LoginScreen.class);
        startActivity(intent);
    }
}