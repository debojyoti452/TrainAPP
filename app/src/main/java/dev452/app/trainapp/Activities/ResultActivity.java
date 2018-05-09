package dev452.app.trainapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dev452.app.trainapp.Controller.ApiKeyClass;
import dev452.app.trainapp.Controller.Adapter.RecyclerAdapter;
import dev452.app.trainapp.Controller.ClassItems.Train;
import dev452.app.trainapp.R;

public class ResultActivity extends AppCompatActivity {
    public String URL_String;
    private RequestQueue queue;
    private List<Train> GetDataAdapter1 = new ArrayList<Train>();

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    public String src,dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//        getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
         src = intent.getStringExtra("src");
         dest = intent.getStringExtra("dest");
//        String date = intent.getStringExtra("date");
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        URL_String = String.format("https://api.railwayapi.com/v2/between/source/"+src+"/dest/"+dest+"/date/"+date+"/apikey/"+ApiKeyClass.API_KEY+"/");


        getJsonObject(URL_String);
    }




    private void getJsonObject(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    //jsonArray
                    JSONArray train = response.getJSONArray("trains");
                    for (int i=0; i<train.length();++i){
//                        Log.d("Trains:",train.toString());
//                        textView.setText(train.toString());
                        //get JSON object
                        Train trainPass = new Train();
                        JSONObject trainDest = train.getJSONObject(i).getJSONObject("to_station");
                        JSONObject trainSrc = train.getJSONObject(i).getJSONObject("from_station");
                        JSONObject destTime = train.getJSONObject(i);
                        JSONObject srcTime = train.getJSONObject(i);
                        JSONObject trainName = train.getJSONObject(i);
                        JSONObject trainNumber = train.getJSONObject(i);
                        JSONObject object = train.getJSONObject(i);

                        //passing to listView
                        trainPass.setSrcName(trainSrc.getString("code"));
                        trainPass.setDestName(trainDest.getString("code"));
                        trainPass.setSourceName(trainSrc.getString("name"));
                        trainPass.setDestinationName(trainDest.getString("name"));
                        trainPass.setSrcTime(srcTime.getString("src_departure_time"));
                        trainPass.setDestTime(destTime.getString("dest_arrival_time"));
                        trainPass.setTrainName(trainName.getString("name"));
                        trainPass.setTrainNumber(trainNumber.getString("number"));

                        //days entry
                        JSONArray inner = object.getJSONArray("days");
                        for (int j=1;j<inner.length();++j) {
                            JSONObject iterate = inner.getJSONObject(j);
//                            Log.d("Days: ", iterate.getString("code"));
 //                           Log.d("Runs: ", iterate.getString("runs"));
//                            String days = iterate.getString("code");
//                            String runs = iterate.getString("runs");
                            trainPass.settDays(iterate.getString("code"));
                            trainPass.settRuns(iterate.getString("runs"));

                        }
//                        Log.d("Source Train: ",trainSrc.getString("code"));
//                        Log.d("Destination Train: ",trainDest.getString("code"));
//                        Log.d("Source Time: ",srcTime.getString("dest_arrival_time"));
//                        Log.d("Destination Time: ",destTime.getString("src_departure_time"));
                        GetDataAdapter1.add(trainPass);
                    }
                    recyclerViewadapter = new RecyclerAdapter(GetDataAdapter1, getApplicationContext());
                    recyclerView.setAdapter(recyclerViewadapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Wrong Station Code.",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Wrong Station Code.",Toast.LENGTH_LONG).show();
            }

        });
        queue.add(jsonObjectRequest);
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//
//                // app icon in action bar clicked; goto parent activity.
//                this.finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
