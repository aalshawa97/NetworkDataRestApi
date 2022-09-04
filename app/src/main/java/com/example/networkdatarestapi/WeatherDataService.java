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

public class WeatherDataService {
    private static Context context;
    //public Context context;

    public static final String QUERY_FOR_CITY_ID = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static final String APPID = "&appid=1f3c5ae0f38df8fd7bc09ad6874a4039";
    //Context context;

    public WeatherDataService(Context context){
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(String cityID);
    }

    public static String getCityID(String cityName, VolleyResponseListener volleyResponseListener) {

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

    /*
    public List<WeatherReportModel> getCityForecastByID(String cityID){
        return " ";
    }

    public List<WeatherReportModel> getCityForecastByName(String cityID){
        return " ";
    }
    */
}
