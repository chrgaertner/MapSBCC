package com.example.mapsbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Routes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        readAddressData();
        
    }

    private List<AddressSample> addressSamples = new ArrayList<>();

    private void readAddressData() {
        InputStream is = getResources().openRawResource(R.raw.adresse);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
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
                
                // print data ud
                for(int i = 0; i < 3; i++){
                    String adr = sample.getAddress();
                    String lat = sample.getLat();
                    String lon = sample.getLon();

                    System.out.println(adr + lat +" - "+ lon);
                }
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data on line " + line, e);
            e.printStackTrace();
        }
    }
}