package project.nathi.tut.ac.za.qualauthapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.NetworkResponse;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import project.nathi.tut.ac.za.qualauthapp.R;
import project.nathi.tut.ac.za.qualauthapp.helper.SessionManager;
import project.nathi.tut.ac.za.qualauthapp.util.AppConfig;


public class LoginUserActivity extends AppCompatActivity {

    private static final String TAG = LoginUserActivity.class.getSimpleName();
    private Button btnLogin;
    private Button lnkRegister;
    private CheckBox loginCheckBox;
    private ProgressDialog pDialog;
    private EditText editEmail;
    private EditText editPassword;
    public String PREFS_USERNAME = "prefsEmail";
    public String PREFS_PASSWORD = "prefsPassword";
    private SessionManager session;
    private SharedPreferences loginPreference;
    private Boolean saveLogin;
    private SharedPreferences.Editor loginPrefEditor;
    String strPassword = String.valueOf("");
    String strEmail = String.valueOf("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);


        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

        }

        btnLogin = (Button) findViewById(R.id.btnLogin);
        lnkRegister = (Button) findViewById(R.id.btnLinkRegister);
        editEmail = (EditText) findViewById(R.id.editEmailAddress);
        editPassword = (EditText) findViewById(R.id.editPassword);
        loginCheckBox = (CheckBox) findViewById(R.id.saveLoginCheckBox);

        loginPreference = getApplicationContext().getSharedPreferences("QualAuthLogin", MODE_PRIVATE);
        loginPrefEditor = loginPreference.edit();

        saveLogin = loginPreference.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            editPassword.setText(loginPreference.getString("password", ""));
            editEmail.setText(loginPreference.getString("email", ""));
            loginCheckBox.setChecked(true);
        }

        pDialog = new ProgressDialog(getApplicationContext());
        pDialog.setCancelable(false);
        pDialog.hide();


        //Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }
        //Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strEmail = editEmail.getText().toString().trim();
                strPassword = editPassword.getText().toString().trim();

                //Check for empty data in the form
                if (!strEmail.isEmpty() && !strPassword.isEmpty()) {
                    if (loginCheckBox.isChecked()) {
                        loginPrefEditor.putBoolean("saveLogin", true);
                        loginPrefEditor.putString("password", strPassword);
                        loginPrefEditor.putString("email", strEmail);
                        loginPrefEditor.commit();
                    } else {
                        loginPrefEditor.clear();
                        loginPrefEditor.commit();
                    }

                    // view.setEnabled(false);

                    //login user



                      checkLogin(strEmail, strPassword);


                } else {
                    //prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter credentials!", Toast.LENGTH_LONG).show();
                }

            }
        });

        lnkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), RegisterUserActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void checkLogin(String username, String password)
    {


        StringRequest postRequest = new StringRequest(com.android.volley.Request.Method.GET, AppConfig.URL_LOGIN + username+"/"+password, new com.android.volley.Response.Listener<String>() {




            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Success "+ response.toString());
              //  hideDialog();
                try {

                    JSONObject jObj = new JSONObject(response);

                        String success = jObj.getString("id");

                        if(success != null)
                        {
                            String fullName = jObj.getString("fullName");
                            String email = jObj.getString("email");
                            String contactNumber = jObj.getString("contactNumber");
                            String address = jObj.getString("address");

                            Toast.makeText(getApplicationContext(),"Successfully Login...",Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("fullName",fullName);
                            intent.putExtra("email",email);

                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Incorrect Email/Password Entered",Toast.LENGTH_LONG).show();
                        }





                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new com.android.volley.Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error response " + error.getMessage());

                        hideDialog();
                    }
                });

        MyApplication.getInstance().addToRequestQueue(postRequest);
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    }



