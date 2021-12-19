package com.example.mapsbcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.api.directions.v5.models.RouteOptions;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerOptions;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationEngineListener,
        PermissionsListener, MapboxMap.OnMapClickListener {

    private MapView mapView;
    private MapboxMap map;
    private Button startButton;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;
    private LocationLayerPlugin locationLayerPlugin;
    private Location orginLocation;
    private Point originPosition;
    private Point destinationPostition;
    private Point desList;
    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;
    private Point p5;
    private Point p6;
    private DirectionsRoute waypointroute;
    private Point[] waypoints = new Point[]{p1};
    private Marker destininationMarker;
    private NavigationMapRoute navigationMapRoute;
    private static final String TAG = "MainActivity";

    //RUC (destination point)
    private double lat = 55.651330;
    private double lng = 12.140020;


    //Bregnevej 24
    private double lat1 = 55.636110;
    private double lng1 = 12.095740;

    //I P Hansens Vej 18
    private double lat2 = 55.637880;
    private double lng2 = 12.062120;

    //Hyldevej 4
    private double lat3 = 55.644920;
    private double lng3 = 12.060460;

    //Liden Kirstens Vej 8
    private double lat4 = 55.652290;
    private double lng4 = 12.095520;

    //Ternevej 47
    private double lat5 = 55.648620;
    private double lng5 = 12.113300;

    //Engblommevej 22
    private double lat6 = 55.634000;
    private double lng6 = 12.109180;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        startButton = findViewById(R.id.startButton);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                        .directionsRoute(waypointroute)
                        //set to false eller fjern helt fra programmet (det er for at simulere at man bevæger sig)
                        .shouldSimulateRoute(true)
                        .build();
                NavigationLauncher.startNavigation(MainActivity.this, options);
            }
        });

    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        map = mapboxMap;

        //RUC
        MarkerOptions options = new MarkerOptions();
        options.title("RUC");
        options.position(new LatLng(lat, lng));
        mapboxMap.addMarker(options);

        //Bregnevej 24
        MarkerOptions options1 = new MarkerOptions();
        options1.title("Bregnevej 24");
        options1.position(new LatLng(lat1, lng1));
        mapboxMap.addMarker(options1);

        //I P Hansens Vej 18
        MarkerOptions options2 = new MarkerOptions();
        options2.title("I P Hansens Vej 18");
        options2.position(new LatLng(lat2, lng2));
        mapboxMap.addMarker(options2);

        //Hyldevej 4
        MarkerOptions options3 = new MarkerOptions();
        options3.title("Hyldevej 4");
        options3.position(new LatLng(lat3, lng3));
        mapboxMap.addMarker(options3);

        //Liden Kirstens Vej 8
        MarkerOptions options4 = new MarkerOptions();
        options4.title("Liden Kirstens Vej 8");
        options4.position(new LatLng(lat4, lng4));
        mapboxMap.addMarker(options4);

        //Ternevej 47
        MarkerOptions options5 = new MarkerOptions();
        options5.title("Ternevej 47");
        options5.position(new LatLng(lat5, lng5));
        mapboxMap.addMarker(options5);

        //Engblommevej 22
        MarkerOptions options6 = new MarkerOptions();
        options6.title("Engblommevej 22");
        options6.position(new LatLng(lat6, lng6));
        mapboxMap.addMarker(options6);


        map.addOnMapClickListener(this);
        enableLocation();
    }

    private void enableLocation() {
        if (permissionsManager.areLocationPermissionsGranted(this)) {
            initializeLocationEnige();
            initializeLocationLayer();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void initializeLocationEnige() {
        locationEngine = new LocationEngineProvider(this).obtainBestLocationEngineAvailable();
        locationEngine.setPriority(LocationEnginePriority.HIGH_ACCURACY);
        locationEngine.activate();

        Location lastLocation = locationEngine.getLastLocation();
        if (lastLocation != null) {
            orginLocation = lastLocation;
            setCameraPostition(lastLocation);
        } else {
            locationEngine.addLocationEngineListener(this);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void initializeLocationLayer() {
        locationLayerPlugin = new LocationLayerPlugin(mapView, map, locationEngine);
        locationLayerPlugin.setLocationLayerEnabled(true);
        locationLayerPlugin.setCameraMode(CameraMode.TRACKING);
        locationLayerPlugin.setRenderMode(RenderMode.NORMAL);
    }

    private void setCameraPostition(Location location) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),
                location.getLongitude()), 13.0));
    }

    @Override
    public void onMapClick(@NonNull LatLng point) {


        // Her laver vi liste med de parameter som viser sig hen vores punkt
        desList = Point.fromLngLat(lng, lat);
        p1 = Point.fromLngLat(lng1, lat1);
        p2 = Point.fromLngLat(lng2, lat2);
        p3 = Point.fromLngLat(lng3, lat3);
        p4 = Point.fromLngLat(lng4, lat4);
        p5 = Point.fromLngLat(lng5, lat5);
        p6 = Point.fromLngLat(lng6, lat6);

        //destinationPostition = Point.fromLngLat(point.getLongitude(), point.getLatitude());
        originPosition = Point.fromLngLat(orginLocation.getLongitude(), orginLocation.getLatitude());
        //Istedet for destionationPosition bruger vi desList som kun viser hen vores punkt.
        getRoute(originPosition, p1,p2,p3,p4,p5,p6, desList);
        startButton.setEnabled(true);
        startButton.setBackgroundResource(R.color.mapbox_blue);
    }


    //Creates the route
    private void getRoute(Point orgin, Point waypoint,Point waypoint1,Point waypoint2,Point waypoint3,Point waypoint4,Point waypoint5, Point destination) {

        NavigationRoute.Builder builder = NavigationRoute.builder()
                .accessToken(Mapbox.getAccessToken())
                .origin(orgin)
                .destination(destination)
                .profile(DirectionsCriteria.PROFILE_DRIVING);
        builder.addWaypoint(waypoint);
        builder.addWaypoint(waypoint1);
        builder.addWaypoint(waypoint2);
        builder.addWaypoint(waypoint3);
        builder.addWaypoint(waypoint4);
        builder.addWaypoint(waypoint5);

        builder.build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        if (response.body() == null) {
                            Log.e(TAG, "No routes found, check right user and access token");
                            return;
                        } else if (response.body().routes().size() == 0) {
                            Log.e(TAG, "No routes found");
                            return;
                        }

                        DirectionsRoute currentRoute = response.body().routes().get(0);

                        waypointroute = currentRoute;

                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        } else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, map);
                        }

                        navigationMapRoute.addRoute(currentRoute);
                    }


                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                        Log.e(TAG, "Error:" + t.getMessage());
                    }

                });
    }

    @Override
    @SuppressWarnings("MissingPermission")
    public void onConnected() {
        locationEngine.requestLocationUpdates();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            orginLocation = location;
            setCameraPostition(location);
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        //present toast or dialog.
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @SuppressWarnings("MissingPermission")
    @Override
    protected void onStart() {
        super.onStart();
        if (locationEngine != null) {
            locationEngine.requestLocationUpdates();
        }
        if (locationLayerPlugin != null) {
            locationLayerPlugin.onStart();
        }
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (locationEngine != null) {
            locationEngine.removeLocationUpdates();
        }
        if (locationLayerPlugin != null) {
            locationLayerPlugin.onStop();
        }
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationEngine != null) {
            locationEngine.deactivate();
        }
        mapView.onDestroy();
    }


}