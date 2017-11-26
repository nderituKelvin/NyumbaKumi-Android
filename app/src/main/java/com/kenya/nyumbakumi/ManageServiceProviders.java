package com.kenya.nyumbakumi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ManageServiceProviders extends AppCompatActivity {

    ListView serviceProvidersList;
    ArrayList<ProvidersListy> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_service_providers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        serviceProvidersList = findViewById(R.id.serviceProvidersListView);
        providers = new ArrayList<ProvidersListy>();

        ProvidersListy provider1 = new ProvidersListy("Nderitu Kelvin", "0705314090");
        ProvidersListy provider2 = new ProvidersListy("Edwin Macha", "0705314090");
        ProvidersListy provider3 = new ProvidersListy("Collins Bwayo", "0705314090");
        ProvidersListy provider4 = new ProvidersListy("Gladys Wangari", "0705314090");
        ProvidersListy provider5 = new ProvidersListy("Joy Wambui", "0705314090");

        providers.add(provider1);
        providers.add(provider2);
        providers.add(provider3);
        providers.add(provider4);
        providers.add(provider5);

        ProvidersAdapter providersAdapter = new ProvidersAdapter(getApplicationContext(), providers);
        serviceProvidersList.setAdapter(providersAdapter);
    }
}
