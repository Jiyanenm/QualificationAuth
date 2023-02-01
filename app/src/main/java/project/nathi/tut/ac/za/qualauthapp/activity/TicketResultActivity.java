package project.nathi.tut.ac.za.qualauthapp.activity;


import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


import project.nathi.tut.ac.za.qualauthapp.R;
import project.nathi.tut.ac.za.qualauthapp.model.Learner;
import project.nathi.tut.ac.za.qualauthapp.model.Qualification;
import project.nathi.tut.ac.za.qualauthapp.util.AppConfig;

public class TicketResultActivity extends AppCompatActivity {

    private static final String TAG = TicketResultActivity.class.getSimpleName();



    private TextView txtFirstName, txtSecondNme, txtLastName, txtIdNumber, txtGenger, txtQualification,txtStatus, txtError;
    private ImageView image;
    private Button btn_viewMore;
    private ProgressBar progressBar;
    private TicketView ticketView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_result);

        Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtFirstName = findViewById(R.id.firstName);
        txtSecondNme = findViewById(R.id.secondName);
        txtLastName = findViewById(R.id.lastName);
        txtIdNumber = findViewById(R.id.lastName);
      txtGenger = findViewById(R.id.lastName);
       txtQualification = findViewById(R.id.txtQualificationNames);
        txtStatus = findViewById(R.id.txtStatus);
        image = findViewById(R.id.image);
        txtError = findViewById(R.id.txt_error);
        ticketView = findViewById(R.id.layout_ticket);
        progressBar = findViewById(R.id.progressBar);

        btn_viewMore = (Button) findViewById(R.id.btn_viewMore);

        btn_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ViewMoreActivity.class);
                startActivity(intent);

            }
        });

        String barcode = getIntent().getStringExtra("code");

        // close the activity in case of empty barcode
        if (TextUtils.isEmpty(barcode)) {
            Toast.makeText(getApplicationContext(), "Barcode is empty!", Toast.LENGTH_LONG).show();
            finish();
        }

        // search the barcode
        test1(barcode);
    }

    /**
     * Searches the barcode by making http call
     * Request was made using Volley network library but the library is
     * not suggested in production, consider using Retrofit
     */
    private void searchBarcode(String barcode) {
        // making volley's json request
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                AppConfig.URL_SEARCH + barcode, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, "Ticket response: " + response.toString());


                        try {
                            JSONArray myJsonArray = new JSONArray(response.toString());
                            JSONObject result = myJsonArray.toJSONObject(myJsonArray);

                            // check for success status
                            if (!response.has("error")) {
                                // received movie response
                                System.out.println("JSSSSSSSSSOOOOOOOONN " + result);
                                renderLearner(result);
                            } else {
                                // no movie found
                                showNoTicket();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }



                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                showNoTicket();
            }
        });

        MyApplication.getInstance().addToRequestQueue(jsonObjReq);
    }
    public void test1(String barcode)
    {
        StringRequest postRequest = new StringRequest(Request.Method.GET, AppConfig.URL_SEARCH+ barcode, new Response.Listener<String>() {



            @Override
            public void onResponse(String s) {

                Log.d(TAG, "Success "+ s.toString());

                try {

                    JSONArray myJsonArray = new JSONArray(s);
                    JSONObject result = myJsonArray.toJSONObject(myJsonArray);

                    System.out.println(result);
                    renderLearner(result);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error response " + error.getMessage());
                    }
                });

        MyApplication.getInstance().addToRequestQueue(postRequest);
    }
    private void showNoTicket() {
        txtError.setVisibility(View.VISIBLE);
        ticketView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }
    /**
     * Rendering movie details on the ticket
     */
    private void renderLearner(JSONObject response) {
        try {

            // converting json to movie object

            Learner learner = new Gson().fromJson(response.toString(), Learner.class);
            Qualification qualificationObj = new Gson().fromJson(response.toString(), Qualification.class);


            JSONObject resobj = new JSONObject(response.toString());
            Iterator<?> keys = resobj.keys();
            while(keys.hasNext() ) {
                String key = (String)keys.next();
                if ( resobj.get(key) instanceof JSONObject ) {
                    JSONObject obj = new JSONObject(resobj.get(key).toString());


                    learner.setFirstName(obj.getString("firstName"));
                    learner.setSecondName(obj.getString("secondName"));
                    learner.setLastName(obj.getString("lastName"));
                    learner.setNationalId(obj.getString("nationalId"));
                    learner.setThumbnailUrl(obj.getString("image"));



                }

            }
















            if (learner != null && qualificationObj != null) {

                txtFirstName.setText(learner.getFirstName());
                txtSecondNme.setText(learner.getSecondName());
                txtLastName.setText(learner.getLastName());
               // txtStatus.setText(qualificationObj.getStatus());
              //  txtQualification.setText(qualificationObj.getQualificationName());
                Glide.with(this).load(learner.getThumbnailUrl()).into(image);

                if (learner.isReleased()) {
                    btn_viewMore.setText(getString(R.string.btn_buy_now));
                    btn_viewMore.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                } else {
                    btn_viewMore.setText(getString(R.string.btn_coming_soon));
                    btn_viewMore.setTextColor(ContextCompat.getColor(this, R.color.btn_disabled));
                }
                ticketView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            } else {
                // movie not found
                showNoTicket();
            }

        } catch (JsonSyntaxException e) {
            Log.e(TAG, "JSON Exception: " + e.getMessage());
            showNoTicket();
            Toast.makeText(getApplicationContext(), e + " Error occurred. Check your LogCat for full report", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // exception
            showNoTicket();
            Toast.makeText(getApplicationContext(), e + " Error occurred. Check your LogCat for full report", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

