package com.kenya.nyumbakumi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity {

    ListView listViewChats;
    ArrayList<ChatListy> chatData;
    Integer user, theLatestChat = 0;
    String phone, idno, photo, confirmed;
    Button chatButton;
    EditText edChatBox;
    ChatListAdapter chatListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // retrieve data from sharedpreferences


        listViewChats = findViewById(R.id.chatListView);
        chatData = new ArrayList<>();

        SharedPreferences userdb = getApplicationContext().getSharedPreferences("USERDATA", MODE_PRIVATE);
        user = userdb.getInt("id", 0);

        chatButton = findViewById(R.id.chatButton);
        edChatBox = findViewById(R.id.edChatBox);
        chatListAdapter = new ChatListAdapter(this, chatData);
        listViewChats.setAdapter(chatListAdapter);

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Response.Listener<String> skizaGuyTwo = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        updateChatView(response);
                        //Log.d("Wangombe", "Response is: "+response);
                    }
                };

                Map<String, String> mapCheck = new HashMap<>();
                mapCheck.put("user", user.toString());
                mapCheck.put("theLatestChat", theLatestChat.toString());

                JSONObject jsonyThree = new JSONObject(mapCheck);
                String newCheckMap = jsonyThree.toString();

                //Log.d("Wangombe", "Checking on Chats update");
                MessangerClass messangerClassCheck = new MessangerClass(getResources().getString(R.string.rootUrl)+"android/updateChat", newCheckMap, skizaGuyTwo);
                RequestQueue lainicheck = Volley.newRequestQueue(getApplicationContext());
                lainicheck.add(messangerClassCheck);
            }
        }, 100, 2000);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get details on chat box
                String message = edChatBox.getText().toString();
                Map<String, String> map = new HashMap<>();
                map.put("user", user.toString());
                map.put("message", message);
                map.put("theLatestChat", theLatestChat.toString());

                JSONObject jsony = new JSONObject(map);
                String newMap = jsony.toString();

                Response.Listener<String> skizaGuy = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        updateChatView(response);
                        edChatBox.setText("");
                    }
                };

                if(message != ""){
                    //attempt to send the message to server and wait for it to come back
                    MessangerClass messangerClass = new MessangerClass(getResources().getString(R.string.rootUrl)+"android/sendChat", newMap, skizaGuy);
                    RequestQueue laini3 = Volley.newRequestQueue(getApplicationContext());
                    laini3.add(messangerClass);
                }else{
                    Toast.makeText(getApplicationContext(), "Empty Message", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void updateChatView(String response){
        //Log.d("Wangombe", response);
       // Log.d("Wangombe", "Attempting to create the object");
        try {
            //Log.d("Wangombe", "Got to create the array");
            JSONArray jsArr = new JSONArray(response);
            for (int i =0 ;i<jsArr.length();++i){
                JSONObject jsonObject = jsArr.getJSONObject(i);
                //Log.d("Wangombe", jsonObject.toString());
                String names = jsonObject.getString("names");
                String wakti = jsonObject.getString("created_at");
                String message = jsonObject.getString("message");
                int idOfChat = jsonObject.getInt("id");
                //Log.d("Wangombe", names+" "+wakti+" "+" "+message);

                chatData.add(new ChatListy(wakti, names, message));
                chatListAdapter.notifyDataSetChanged();

                //also. find the largest ID received and update(latestChat Parameter)
                if(idOfChat > theLatestChat){
                    theLatestChat = idOfChat;
                    //Log.d("Wangombe", theLatestChat.toString());
                }

                //Log.d("Wangombe", names+" "+wakti+" "+" "+message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            //Log.d("Wangombe", e.toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.member_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.goToServiceProviderMenuOption:
                Toast.makeText(getApplicationContext(), "Going To Service Providers", Toast.LENGTH_SHORT).show();
                Intent goToServiceProvider = new Intent(getApplicationContext(), ContactServiceProvider.class);
                startActivity(goToServiceProvider);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
