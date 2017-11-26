package com.kenya.nyumbakumi;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nderitu on 16/11/2017.
 */

public class MessangerClass extends StringRequest{
    private Map<String, String> params;

    public MessangerClass(String theUrl, String theData, Response.Listener<String> listener) {
        super(Method.POST, theUrl, listener, null);
        try{
            Log.d("Wangombe", theUrl);
            Log.d("Wangombe", theData);
            params = new HashMap<>();
            params.put("theJson", theData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}
