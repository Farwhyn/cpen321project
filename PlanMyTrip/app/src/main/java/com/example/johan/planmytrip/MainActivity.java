package com.example.johan.planmytrip;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public TextView text_view;
    public JSONObject responseJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        text_view = (TextView) this.findViewById(R.id.text_field);
        text_view.setText("Blabla");
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.translink.ca/rttiapi/v1/stops/60980/estimates?apikey=1Y8IBRRxW0yYIhxyWswH";

        myJSONObjectRequest(url);

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


    public void myJSONObjectRequest(String url){

        RequestQueue queue = Volley.newRequestQueue(this);

        Response.Listener<JSONObject> myResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                text_view.setText("Response is: " + response.toString());
                responseJSON = response;
            }
        };

        Response.ErrorListener myErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                text_view.setText("That didn't work! " + error.toString());
            }
        };


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,myResponseListener,myErrorListener)
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/JSON");
                return params;
            }

        };

        queue.add(jsonRequest);


    }


    public void myJSONArrayRequest(String url){

        RequestQueue queue = Volley.newRequestQueue(this);

        Response.Listener<JSONArray> myResponseListener = new Response.Listener<JSONArray>()
        {

            @Override
            public void onResponse(JSONArray response) {
                text_view.setText("Response is: " + response.toString());


            }

        };
        Response.ErrorListener myErrorListener = new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                text_view.setText("That didn't work! " + error.toString());

            }

        };



        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,myResponseListener,myErrorListener)
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/JSON");
                return params;
            }

        };

        queue.add(jsonRequest);


    }

}