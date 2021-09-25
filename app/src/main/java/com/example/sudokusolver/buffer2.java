package com.example.sudokusolver;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class buffer2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer2);

        String unsolved = getIntent().getStringExtra("unsolved_sudoku");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://sudoku-solving-algorithm.herokuapp.com/";
        TextView textView = (TextView) findViewById(R.id.textView3);


        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("unsolved_sudoku", unsolved);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textView.setText(response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(buffer2.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {

//            /**
//             * Passing some request headers
//             **/
//            @Override
//            public Map<String, String> getHeaders() {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }


        };

        // Adding request to request queue
        queue.add(jsonObjReq);




    }
}