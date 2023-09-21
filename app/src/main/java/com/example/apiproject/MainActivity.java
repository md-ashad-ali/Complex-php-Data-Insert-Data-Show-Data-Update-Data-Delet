package com.example.apiproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText nameET,numberET,mailEt;
    Button insertData;
    ListView listView;

    HashMap<String,String>hashMap;
    ArrayList<HashMap<String,String>>arrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FIND_VIEW_ID();
        DATA_INSERT_SERVER();
        SHOW_DATA_SERVER();




    }
    //================ show data ==================================
    private void SHOW_DATA_SERVER() {

        arrayList = new ArrayList<>();

        String url = "https://outstandingacademy.000webhostapp.com/myServer/ShowData.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0; i<response.length(); i++)
                {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String id = jsonObject.getString("Id");
                        String name = jsonObject.getString("Name");
                        String number = jsonObject.getString("Number");
                        String email = jsonObject.getString("Email");

                        hashMap = new HashMap<>();

                        hashMap.put("id",id);
                        hashMap.put("name",name);
                        hashMap.put("number",number);
                        hashMap.put("email",email);
                        arrayList.add(hashMap);




                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                }

                Mylist mylist = new Mylist();
                listView.setAdapter(mylist);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);


    }

    //================ show data ==================================


    //================ insert data ==================================
    private void DATA_INSERT_SERVER() {

        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name =nameET.getText().toString();
                String number =numberET.getText().toString();
                String email =mailEt.getText().toString();

                String url = "https://outstandingacademy.000webhostapp.com/myServer/DataInsert.php"+"?"+"n="+name+"&p="+number+"&e="+email;

                StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Data inser succfesfully")
                                .show();

                        SHOW_DATA_SERVER();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);



            }
        });


    }
    //================ insert data ==================================

    private void FIND_VIEW_ID() {

        nameET = findViewById(R.id.name);
        numberET = findViewById(R.id.number);
        mailEt = findViewById(R.id.email);
        insertData = findViewById(R.id.insertData);
        listView = findViewById(R.id.listview);

    }


    private class Mylist extends BaseAdapter{


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

        //================ show data ==================================================================================

            LayoutInflater layoutInflater = getLayoutInflater();
            View myview = layoutInflater.inflate(R.layout.item_design,listView,false);

            TextView name,email,number;
            Button update,delet;

            name = myview.findViewById(R.id.item_name);
            number = myview.findViewById(R.id.item_number);
            email = myview.findViewById(R.id.item_email);
            update = myview.findViewById(R.id.item_update_button);
            delet = myview.findViewById(R.id.item_delet_button);

            HashMap<String,String>myMap = arrayList.get(i);

            String ids = myMap.get("id");
            String names = myMap.get("name");
            String numbers = myMap.get("number");
            String emails = myMap.get("email");


            name.setText(names);
            number.setText(numbers);
            email.setText(emails);


        //================ show data ====================================================================================




            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name =nameET.getText().toString();
                    String number =numberET.getText().toString();
                    String email =mailEt.getText().toString();

                    String url = "https://outstandingacademy.000webhostapp.com/myServer/DataUpdate.php"+"?"+"io="+ids+"&no="+name+"&po="+number+"&eo="+email;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Update succesfully")
                                    .show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            SHOW_DATA_SERVER();

                        }
                    });


                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);


                }
            });

            delet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String url = "https://outstandingacademy.000webhostapp.com/myServer/DeletData.php"+"?"+"ie="+ids;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Data Delet Succesfully")
                                    .show();

                            SHOW_DATA_SERVER();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);


                }
            });


            return myview;
        }
    }


}