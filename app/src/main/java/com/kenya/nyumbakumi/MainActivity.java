package com.kenya.nyumbakumi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edPhoneNumber = findViewById(R.id.edTextLoginPhone);
        final EditText edIdNumber = findViewById(R.id.edTextIdno);


        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String idnumber = edIdNumber.getText().toString();
                final String phonenumber = edPhoneNumber.getText().toString();

                Map<String, String> map = new HashMap<String, String>();
                map.put("phone", phonenumber);
                map.put("idno", idnumber);

                JSONObject jsony = new JSONObject(map);
                String newMap = jsony.toString();

                Response.Listener<String> skizaGuy = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            final JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("login");
                            if(success){
                                //Proceed to attempt Login
                                String confirmed = jsonObject.getString("confirmed");
                                if(confirmed == "0"){
                                    AlertDialog.Builder alerty = new AlertDialog.Builder(getApplicationContext(), R.style.DialogTheme);
                                    alerty.setMessage("You have been added to the nyumba kumi group.., accept to join");
                                    edPhoneNumber.setText("");
                                    edIdNumber.setText("");
                                    alerty.setPositiveButton("Join", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            // change the users confirmed to 1:
                                            Map<String, String> confirmMap = new HashMap<>();
                                            confirmMap.put("confirm", "1");
                                            JSONObject jsoncon = new JSONObject(confirmMap);
                                            String newConfirmMap = jsoncon.toString();
                                            try {
                                                confirmMap.put("user", jsonObject.getString("id"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            Response.Listener<String> confirmationListener = new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    // now go to confirmed
                                                    Log.d("Wangombe", response);
                                                    try {
                                                        JSONObject confirmObj = new JSONObject(response);
                                                        Boolean confirmedbool = confirmObj.getBoolean("confirmed");
                                                        if(confirmedbool){
                                                            //push to next page
                                                            toHome(jsonObject);
                                                        }else{
                                                            Toast.makeText(getApplicationContext(), "An Error Occurred", Toast.LENGTH_LONG).show();
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            };

                                            MessangerClass setConfirmedToOne = new MessangerClass(getResources().getString(R.string.rootUrl)+"android/confirmjoin", newConfirmMap, confirmationListener);
                                            RequestQueue line2 = Volley.newRequestQueue(getApplicationContext());
                                            line2.add(setConfirmedToOne);

                                            Toast.makeText(getApplicationContext(), "You have Joined Makutano Nyumba Kumi", Toast.LENGTH_SHORT).show();
                                            Intent toHome = new Intent(getApplicationContext(), Home.class);
                                            startActivity(toHome);
                                            finish();
                                        }
                                    });
                                    alerty.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(getApplicationContext(), "You Cancelled", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    alerty.show();
                                }else{
                                    // Log them in bila shida
                                    toHome(jsonObject);
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.d("Wangombe", response);
                    }
                };

                try{
                    MessangerClass messangerClass = new MessangerClass(getResources().getString(R.string.rootUrl)+"android/login", newMap, skizaGuy);
                    RequestQueue laini = Volley.newRequestQueue(getApplicationContext());
                    laini.add(messangerClass);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Network Connection Error", Toast.LENGTH_LONG).show();
                    Log.d("Wangombe", "Network Connection Error");
                }
            }

        });
    }

    public void toHome(JSONObject jsonObject){
        //push to next page
        Intent toHome = new Intent(getApplicationContext(), Home.class);
        SharedPreferences userData = getApplicationContext().getSharedPreferences("USERDATA", MODE_PRIVATE);
        SharedPreferences.Editor userWriter = userData.edit();
        try {
            userWriter.putInt("id", jsonObject.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            userWriter.putString("names", jsonObject.getString("names"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            userWriter.putString("phone", jsonObject.getString("phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            userWriter.putString("idno", jsonObject.getString("idno"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            userWriter.putString("photo", jsonObject.getString("photo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            userWriter.putInt("admin", jsonObject.getInt("admin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            userWriter.putInt("group", jsonObject.getInt("group"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            userWriter.putString("confirmed", jsonObject.getString("confirmed"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        userWriter.apply();
        startActivity(toHome);
        finish();
    }
}