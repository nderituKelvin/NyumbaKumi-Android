package com.kenya.nyumbakumi;

import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AlertDialogLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminRegister extends AppCompatActivity {

    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        register = findViewById(R.id.adminButtonRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Confirm Registration
                AlertDialog.Builder alerty = new AlertDialog.Builder(view.getContext());
                alerty.setTitle("Continue??");
                alerty.setMessage("By Confirming Registration, You will be ejected from your current Nyumba Kumi Account");
                alerty.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "You Confirmed Registration", Toast.LENGTH_SHORT).show();
                    }
                });
                alerty.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "You Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                alerty.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(getParentActivityIntent() == null){
                    Log.i("Hoohaa", "You forgot to specify the parent activity, I ain't doing nothing man");
                    onBackPressed();
                }else{
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
