package com.silentsquad.sudokusolver;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import android.util.Base64;


public class buffer extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        MainApplication app = (MainApplication) getApplication();
        if(app.ring_start) {
            app.ring.start();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApplication app = (MainApplication) getApplication();
        if(app.ring_start) {
            app.ring.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainApplication app = (MainApplication) getApplication();
        app.ring.pause();

    }


    private static final int MY_SOCKET_TIMEOUT_MS = 50000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String imagepath = getIntent().getStringExtra("imagepath");

        File file = new File(imagepath);

        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Toast.makeText(this, encodedfile, Toast.LENGTH_LONG).show();
        //   TextView textView = (TextView) findViewById(R.id.textView);

        //  textView.setText(encodedfile);



//        File path = this.getFilesDir();
//
//        File filewe = new File(""+getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/", "my-file-name.txt");
//
//        FileOutputStream stream = null;
//        try {
//            stream = new FileOutputStream(filewe);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            stream.write(encodedfile.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                stream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//




        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://sudoku-grid-digit-recognition.herokuapp.com/";
        ImageView gif=(ImageView)findViewById(R.id.gifImageView);

        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("image", encodedfile);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        textView.setText(response.toString());
//                        gif.setImageDrawable(null);

                        Intent gotobuffer2 = new Intent(getApplicationContext(),com.silentsquad.sudokusolver.buffer2.class) ;
                        try {
                            gotobuffer2.putExtra("predictions", (String) response.get("predictions"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(gotobuffer2);
                        finish();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(buffer.this, "Fail to get response = " + error +" \n Please try again.", Toast.LENGTH_SHORT).show();
                finish();
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

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        queue.add(jsonObjReq);

        // Cancelling request
    /* if (queue!= null) {
    queue.cancelAll(TAG);
    } */



    }
}













//        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
//        TextView textView = (TextView) findViewById(R.id.textView);
//
//
//        String url = "https://sudoku-grid-digit-recognition.herokuapp.com/";
//        String finalEncodedfile = encodedfile;
//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                textView.setText(response);
//            }
//        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(buffer.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            protected Map<String, String> getParams() {
//
//                Map<String, String> MyData = new HashMap<String, String>();
//
//                MyData.put("image", finalEncodedfile); //Add the data you'd like to send to the server.
//                return MyData;
//            }
//        };
//
//
//        MyRequestQueue.add(MyStringRequest);
//
//    }

























//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="https://sudoku-grid-digit-recognition.herokuapp.com/";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        textView.setText(response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
//            }
//        });
//
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);

























//        URL url = null;
//        try {
//            url = new URL("https://sudoku-grid-digit-recognition.herokuapp.com/");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        HttpURLConnection con = null;
//        try {
//            con = (HttpURLConnection)url.openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            con.setRequestMethod("POST");
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        }
//        con.setRequestProperty("Content-Type", "application/json; utf-8");
//        con.setRequestProperty("Accept", "application/json");
//        con.setDoOutput(true);
//
//        String jsonInputString = "{"+encodedfile+"}";
//
//        try(OutputStream os = con.getOutputStream()) {
//            byte[] input = jsonInputString.getBytes("utf-8");
//            os.write(input, 0, input.length);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        try(BufferedReader br = new BufferedReader(
//                new InputStreamReader(con.getInputStream(), "utf-8"))) {
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            System.out.println(response.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");



//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String encodedString = Base64.encodeToString(b, Base64.URL_SAFE | Base64.NO_WRAP);








//        File f = new File(imagepath);
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(f);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        byte byteArray[] = new byte[(int)f.length()];
//        try {
//            fis.read(byteArray);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String imageString = Base64.encodeBase64String(byteArray);








//        byte[] fileContent = new byte[0];
//        try {
//            fileContent = FileUtils.readFileToByteArray(new File(imagepath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            String encodedString = Base64.getEncoder().encodeToString(fileContent);
//        }


