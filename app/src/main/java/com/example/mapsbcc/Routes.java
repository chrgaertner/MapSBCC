package com.example.mapsbcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Routes extends AppCompatActivity {

    // Assign url to the source of our JSON data
    private static final String url = "http://mob5g.net/locations.php";
    // Create RecyclerView for displaying data
    RecyclerView recview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        // Execute processdata()
        processdata();
    }

    public void processdata() {   // Create a variable which sends a request with the url variable as parameter
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            // If response received execute function
            public void onResponse(String response) {
                // Utilizing the Gson library to serialize JSON to Java objects.
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                // Store the data from the JSON response into a variable
                location data[] = gson.fromJson(response, location[].class);

                // Create a new object where we pass the response data to Adapter class
                myadapter adapter = new myadapter(data);
                // Set Adapter to create textviews for displaying response data
                recview.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            // If we receive an error on our response, we will display a textbox containing the error message.
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }
        );

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

}