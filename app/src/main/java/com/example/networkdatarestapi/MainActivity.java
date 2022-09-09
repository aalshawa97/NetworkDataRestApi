package com.example.networkdatarestapi;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.networkdatarestapi.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
    {"coord":{"lon":34.4667,"lat":31.5},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"base":"stations","main":{"temp":299.75,"feels_like":299.75,"temp_min":299.75,"temp_max":299.75,"pressure":1009,"humidity":68,"sea_level":1009,"grnd_level":1004},"visibility":10000,"wind":{"speed":2.69,"deg":334,"gust":3},"clouds":{"all":0},"dt":1662398575,"sys":{"type":2,"id":2002441,"country":"PS","sunrise":1662348016,"sunset":1662393720},"timezone":10800,"id":281133,"name":"Gaza","cod":200}
     */

    public static final String QUERY_FOR_CITY_ID = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static final String APP_ID = "&appid=1f3c5ae0f38df8fd7bc09ad6874a4039";
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    //Define all the components that are in the XML file
    Button btn_getCityId, btn_getWeatherById, btn_getWeatherByName;
    EditText etTextPersonName;
    ListView lv_forecastList;

    //This is the data that will be shown in the listview component.
    List<WeatherReportModel> forecastList = new ArrayList<>();

    //Default value for city is not provided by the user.
    String cityName = "Phoenix";

    public List<WeatherReportModel> getCityForecastByID(String cityID){
        LinkedList<WeatherReportModel> myReport = new LinkedList<>();
        return myReport;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Assign variables and values to all of the items in the XML layout file.
        btn_getCityId = findViewById(R.id.btn_getCityId);
        btn_getWeatherById = findViewById(R.id.btn_useCityId);
        btn_getWeatherByName = findViewById(R.id.btn_useCityName);
        lv_forecastList = findViewById(R.id.lv_weatherReport);
        etTextPersonName = findViewById(R.id.editTextTextPersonName);

        final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

        //Click listeners for each button
        btn_getWeatherById.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked me!", Toast.LENGTH_LONG).show();

                WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
                weatherDataService.getCityForecastByID(etTextPersonName.getText().toString(), new WeatherDataService.ForecastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something is wrong " +message, Toast.LENGTH_LONG).show();
                    }

                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        //Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_LONG).show();
                        //Put the entire list into the listview control
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModel);
                    }

                    @Override
                    public void onResponse(String cityID) {
                        //Toast.makeText(MainActivity.this, we, Toast.LENGTH_LONG).show();
                    }
                });
                String city = weatherDataService.getCityID(etTextPersonName.getText().toString(), new WeatherDataService.ForecastByIDResponse(){
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something is wrong ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this, "Returned an ID of " + cityID, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btn_getCityId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

                String city = weatherDataService.getCityID(etTextPersonName.getText().toString(), new WeatherDataService.ForecastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something is wrong ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this, "Returned an ID of " + cityID, Toast.LENGTH_LONG).show();
                    }
                });
                Toast.makeText(MainActivity.this, "Returned an ID of " + city, Toast.LENGTH_SHORT).show();
                //final TextView textView = (TextView) findViewById(R.id.text);

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = QUERY_FOR_CITY_ID + etTextPersonName.getText() + APP_ID;

                final String[] cityID = {""};
                String cityId = WeatherDataService.getCityID(etTextPersonName.getText().toString(), new WeatherDataService.ForecastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something wrong : " + message.toString(), Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this, "Returned an ID of: " + cityID, Toast.LENGTH_LONG);
                    }
                });
                //JSONObject Jarray = new JSONObject(result);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject cityInfo = response;
                            cityID[0] = cityInfo.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, "City ID = " + cityID[0], Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

                /*

                RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://itunes.apple.com/search?term=michael+jackson";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    int resultCount = response.optInt("resultCount");
                    if (resultCount > 0) {
                        Gson gson = new Gson();
                        JSONArray jsonArray = response.optJSONArray("results");
                        if (jsonArray != null) {
                            SongInfo[] songs = gson.fromJson(jsonArray.toString(), SongInfo[].class);
                            if (songs != null && songs.length > 0) {
                                for (SongInfo song : songs) {
                                    Log.i("LOG", song.trackViewUrl);
                                }
                            }
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
                 */
/*
                JSONObject object = new JSONObject("");
                JSONArray Jarray  = object.getJSONArray("contacts");

                for (int i = 0; i < Jarray.length(); i++)
                {
                    JSONObject Jasonobject = Jarray.getJSONObject(i);
                }*/
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Toast.makeText(MainActivity.this, "City ID: " + response.toString(), Toast.LENGTH_LONG).show();
                                //Toast.makeText((MainActivity.this,"Response is: " + response.substring(0,500), Toast.LENGTH_LONG)).showText();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("That didn't work!");
                        Toast.makeText(MainActivity.this, "That did not work!", Toast.LENGTH_LONG).show();
                    }
                });

                queue.add(stringRequest);
                Toast.makeText(MainActivity.this, "You clicked me!", Toast.LENGTH_LONG).show();

                // Add the request  to the RequestQueue.
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked me " + etTextPersonName.getText(), Toast.LENGTH_LONG).show();

                weatherDataService.getCityForecastByName(etTextPersonName.getText().toString(), new WeatherDataService.ForecastByIDResponse() {

                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(String cityID) {
                        
                    }

                    // @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        //Toast.makeText(MainActivity.this, we, Toast.LENGTH_LONG).show();
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                    }
                });
            }
        });

        //setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        /*
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}