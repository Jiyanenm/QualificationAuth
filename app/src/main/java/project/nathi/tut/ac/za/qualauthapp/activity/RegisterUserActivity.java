package project.nathi.tut.ac.za.qualauthapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import project.nathi.tut.ac.za.qualauthapp.R;
import project.nathi.tut.ac.za.qualauthapp.helper.SessionManager;
import project.nathi.tut.ac.za.qualauthapp.util.AppConfig;
import project.nathi.tut.ac.za.qualauthapp.util.AppController;

public class RegisterUserActivity extends AppCompatActivity {

    private  static  final String TAG = RegisterUserActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private Button btnRegister;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputContactNumber;
    private EditText inputAddress;
    private EditText inputPassword;
    private EditText inputConfirmPassword;
    private SessionManager session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        btnRegister = (Button) findViewById(R.id.btnRegister);
        inputFullName = (EditText) findViewById(R.id.editFullName);
        inputEmail = (EditText) findViewById(R.id.editEmailAddress);
        inputContactNumber = (EditText) findViewById(R.id.editContactNumber);
        inputAddress = (EditText) findViewById(R.id.editAddress);
        inputPassword = (EditText) findViewById(R.id.editPassword);
        inputConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        //Check if user already logged in or not
        if(session.isLoggedIn())
        {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterUserActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String fullName = inputFullName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String contactNumbers = inputContactNumber.getText().toString().trim();
                String address = inputAddress.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String confPassword = inputConfirmPassword.getText().toString().trim();

                // txtImage.setText("File name : " + image.toString());


                if(!fullName.isEmpty() && !email.isEmpty() && !contactNumbers.isEmpty() && !address.isEmpty() && !password.isEmpty() && !confPassword.isEmpty())
                {
                    if(!password.toString().equals(confPassword.toString()))
                    {
                        Toast.makeText(getApplicationContext(),"Password and Confirm Password Not Matching!",Toast.LENGTH_LONG).show();
                        inputConfirmPassword.requestFocus();
                    }
                    else
                    {
                       // registerUser(fullName,email,contactNumbers,address,password);
                        try {
                            Register2(fullName,email,contactNumbers,address,password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please complete all the fields to register",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void Register2(final String fullName, final String email, final  String contactNumber , final  String address, final String password) throws JSONException {

        final AlertDialog.Builder ad = new AlertDialog.Builder(this);
        pDialog.setMessage("Registering...");


        showDialog();

        JSONObject postparams=new JSONObject();
        postparams.put("fullName", fullName);
        postparams.put("email", email);
        postparams.put("contactNumber", contactNumber);
        postparams.put("address", address);
        postparams.put("password", password);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, postparams, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d(TAG, "Register Response : " + response.toString());
                        hideDialog();
                        //Success Callback

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Failure Callback
                        hideDialog();
                    }
                });

        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();
        MyApplication.getInstance().addToRequestQueue(jsonObjReq,"postRequest");

        Intent intent = new Intent(RegisterUserActivity.this, LoginUserActivity.class);
        startActivity(intent);

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
