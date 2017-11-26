package com.kenya.nyumbakumi;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ContactServiceProvider extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> names;
    Spinner spinner;
    JSONArray jsArr;
    Button sendTextBtn;
    EditText editTextSMS;
    String phone, theMessage;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_service_provider);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editTextSMS = findViewById(R.id.enterMessageSMS);
        sendTextBtn = findViewById(R.id.sendMessageButton);


        sendTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theMessage = editTextSMS.getText().toString();
                if(phone == "" || theMessage == ""){
                    Log.d("Wangombe", "Clicked on send SMS");
                    Toast.makeText(getApplicationContext(), "Phone and Message cannot be empty", Toast.LENGTH_LONG).show();
                }else{
                    //attempt to send the text sms now
                    SmsManager  smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, theMessage, null, null);
                    Log.d("Wangombe", "Sending SMS");
                    Toast.makeText(ContactServiceProvider.this, theMessage, Toast.LENGTH_SHORT).show();
                    editTextSMS.setText("");
                }
            }
        });

        spinner = findViewById(R.id.serviceCategorySelect);
        spinner.setOnItemSelectedListener(this);
        names = new ArrayList<String>();




        SharedPreferences userdb = getApplicationContext().getSharedPreferences("USERDATA", MODE_PRIVATE);
        Integer user = userdb.getInt("id", 0);
        Response.Listener<String> skizaGuy = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Wangombe", "Services Response " +response);
                SharedPreferences serviceData = getApplicationContext().getSharedPreferences("SERVICEDATA", MODE_PRIVATE);
                SharedPreferences.Editor svcwriter = serviceData.edit();
                svcwriter.putString("svcs", response);
                svcwriter.apply();

                try {
                    jsArr = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i =0 ;i<jsArr.length();++i){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = jsArr.getJSONObject(i);
                        names.add(jsonObject.getString("name") + " - " + jsonObject.getString("type"));
                        Log.d("Wangombe", "Value is: "+jsonObject.getString("type"));
                        names.remove("");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Map<String, String> mapCheck = new HashMap<>();
        mapCheck.put("user", user.toString());
        JSONObject jsonyThree = new JSONObject(mapCheck);
        String newCheckMap = jsonyThree.toString();
        MessangerClass messangerClassCheck = new MessangerClass(getResources().getString(R.string.rootUrl)+"android/getServices", newCheckMap, skizaGuy);
        Log.d("Wangombe", "Fetching Types");
        RequestQueue lainicheck = Volley.newRequestQueue(getApplicationContext());
        lainicheck.add(messangerClassCheck);

        names.add("");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Log.d("Wangombe", "Selected Called");
        try {
            SharedPreferences svcdb = getApplicationContext().getSharedPreferences("SERVICEDATA", MODE_PRIVATE);
            String svc = svcdb.getString("svcs", "");

            Log.d("Wangombe", "Database read: "+svc);

            JSONArray jsa2 = new JSONArray(svc);
            JSONObject jsob = jsa2.getJSONObject(i);

            phone = jsob.getString("phone");
            Toast.makeText(getApplicationContext(), phone, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
