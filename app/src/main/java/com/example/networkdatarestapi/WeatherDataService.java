package com.example.networkdatarestapi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {
    private static Context context;
    String cityID;
    //public Context context;

    public static final String QUERY_FOR_CITY_ID = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static final String APPID = "&appid=1f3c5ae0f38df8fd7bc09ad6874a4039";
    //Context context;

    public WeatherDataService(Context context){
        this.context = context;
    }

    public interface ForecastByIDResponse{
        void onError(String message);

        void onResponse(String cityID);
    }

    public static String getCityID(String cityName, ForecastByIDResponse volleyResponseListener) {

        // Instantiate the RequestQueue.
        //RequestQueue queue = Volley.newRequestQueue(WeatherDataService.this);
        //String city = "Kabul";
        String url = QUERY_FOR_CITY_ID + cityName + APPID;

        final String[] cityID = {""};
        Log.d("Weather Data Service: ", "getCityID is working!");
        //Context context;
        //Toast.makeText( "", Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject cityInfo = response;
                    cityID[0] = cityInfo.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //This worked, but it didn't return the id number to the MainActivity
                Toast.makeText(context, "City ID = " + cityID[0], Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                volleyResponseListener.onError("Something went wrong");
                volleyResponseListener.onResponse(cityID[0]);
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);

        //May return a null which is a problem
        try{

        }
        catch (Exception e){

        }
        return  cityID[0];
    }

    public void getCityForecastByID(String cityID, ForecastByIDResponse forecastByIDResponse){
        List<WeatherReportModel> report = new ArrayList<>();

       //Get the JSON object
        String url = QUERY_FOR_CITY_ID + cityID;
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                try {
                    List<JSONObject> consolidated_weather_list = (List<JSONObject>) response.getJSONObject("consolidated weather");
                    WeatherReportModel first_day = new WeatherReportModel();
                    String wind_direction_compass;
                    float temperature = 0.0F;
                    float temperatureMin = 0.0F;
                    float temperatureMax = 0.0F;
                    float pressure = 0.0F;
                    float humidity = 0;
                    float seaLevel = 0;
                    float groundLevel = 0;
                    float visibility = 0;
                    float distance = 0.0F;

                    JSONObject first_day_from_api = consolidated_weather_list.get(0);
                    first_day.setId(first_day_from_api.getInt("id"));
                    first_day.setWeather_state_name((String) first_day_from_api.get("weather_state_name"));
                    first_day.setWeather_state_abbr(first_day_from_api.getString("weather_state_abbr"));
                    first_day.setWind_direction_compass(first_day_from_api.getString("wind_direction_compass"));
                    //first_day.setGroundLevel(first_day_from_api.getString(1.9f));
                    first_day.setGroundLevel((float) first_day_from_api.getDouble("groundLevel"));
                    first_day.setDistance((float)first_day_from_api.getDouble("distance"));
                    first_day.setHumidity((float)first_day_from_api.getDouble("humidity"));
                    first_day.setLongitude((float)first_day_from_api.getDouble("longitude"));
                    first_day.setLatitude((float)first_day_from_api.getDouble("latitude"));
                    first_day.setPressure((float)first_day_from_api.getDouble("pressure"));
                    first_day.setSeaLevel((float) first_day_from_api.getDouble("seaLevel"));
                    first_day.setTemperature((float) first_day_from_api.getDouble("temperature"));
                    first_day.setTemperatureMax((float) first_day_from_api.getDouble("temperatureMax"));
                    first_day.setTemperatureMin((float) first_day_from_api.getDouble("temperatureMin"));

                    //forecastByIDResponse.onResponse(first_day);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //return report;
    }
/*
    public List<WeatherReportModel> getCityForecastByName(String cityID){
        return " ";
    }
    */
}
