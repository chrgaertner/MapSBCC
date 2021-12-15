package com.example.mapsbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Routes extends AppCompatActivity {

    ListView listView;

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

                listView = (ListView) findViewById(R.id.listview);
                ArrayList<AddressSample> arrayList = new ArrayList<>();


                for (int i = 0; i < addressSamples.size(); i++) {
                    arrayList.add(addressSamples.get(i));
                }

                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

                listView.setAdapter(arrayAdapter);




                System.out.println(sample.getAddress() + " TEST");



            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data on line " + line, e);
            e.printStackTrace();
        }
    }
}