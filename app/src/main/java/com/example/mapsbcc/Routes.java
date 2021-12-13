package com.example.mapsbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Routes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);
        readAddressData();

    }

    private final List<AddressSample> addressSamples = new ArrayList<>();

    private void readAddressData() {
        InputStream is = getResources().openRawResource(R.raw.adresse);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );

        String line = "";

        try {
            //Gå forbi overskrifter
            reader.readLine();

            while (((line = reader.readLine()) != null)) {
                //Opdel ved ;
                String[] tokens = line.split(";");

                //Læs data
                AddressSample sample = new AddressSample();
                sample.setAddress(tokens[0]);
                sample.setLat(tokens[1]);
                sample.setLon(tokens[2]);
                addressSamples.add(sample);

                Log.d("MyActivity", "Just created " + sample);

                RelativeLayout re = (RelativeLayout) findViewById(R.id.main);

                for (int i = 0; i < 3; i++) {
                    TextView text = new TextView(this);
                    text.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    text.setText(sample.getAddress()+i);
                    re.addView(text);
                }




                System.out.println(sample.getAddress() + " TEST");





            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data on line " + line, e);
            e.printStackTrace();
        }
    }
}