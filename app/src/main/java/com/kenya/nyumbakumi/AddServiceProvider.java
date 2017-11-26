package com.kenya.nyumbakumi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddServiceProvider extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_provider);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner selectServiceType = findViewById(R.id.serviceCategory);
        selectServiceType.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Fire");
        categories.add("Police");
        categories.add("Medical");
        categories.add("Security");
        categories.add("Transport");
        categories.add("Food Services");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectServiceType.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String item = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
        //Add Logic To The selection like setting the global variable to the recived item

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
