package com.nextGenIndo.jahitrush;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText Email, Password;
    private Button buttonSignIn;
    private RequestQueue requestQueue;
    private static final String URL = "http://www.jahit-rush.com/api/index.php/register/login_post/";
    private StringRequest SRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = findViewById(R.id.editText1);
        Password = findViewById(R.id.editText3);
        buttonSignIn = findViewById(R.id.buttonSubmit);

        requestQueue = Volley.newRequestQueue(this);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject Jsonobject = new JSONObject(response);
                            if(Jsonobject.names().get(0).equals("success")){
                                Toast.makeText(getApplicationContext(),"SUCCESS"+Jsonobject.getString("success"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,Welcommen.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "ERROR" + Jsonobject.getString("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> HM = new HashMap<String, String>();
                        HM.put("email", Email.getText().toString());
                        HM.put("password", Password.getText().toString());

                        return HM;
                    }
                };
            }
        });

    }
}
