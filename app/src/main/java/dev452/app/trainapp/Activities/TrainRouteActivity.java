package dev452.app.trainapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
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
import java.util.ArrayList;
import java.util.List;

import dev452.app.trainapp.Controller.ApiKeyClass;
import dev452.app.trainapp.Controller.Adapter.RouteRecyclerAdapter;
import dev452.app.trainapp.Controller.ClassItems.TrainStation;
import dev452.app.trainapp.R;

public class TrainRouteActivity extends AppCompatActivity {
    public String URL_Route;
    private RecyclerView recyclerView;
    private List<TrainStation> trainStations = new ArrayList<TrainStation>();
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    private RequestQueue queue;
    public String codeDest, nameDest, srcMark;
    public ImageView marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_route);
        recyclerView = findViewById(R.id.routeRecyclerView);
        recyclerViewlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        queue = Volley.newRequestQueue(this);
        marker = findViewById(R.id.nextLocationMarker);
        Intent intent = getIntent();
        String trainNo =  intent.getStringExtra("trainNo");
        codeDest = intent.getStringExtra("codeDest");
        nameDest = intent.getStringExtra("nameDest");
        srcMark = intent.getStringExtra("nameSrc");
        URL_Route = String.format("https://api.railwayapi.com/v2/route/train/"+trainNo+"/apikey/"+ApiKeyClass.API_KEY+"/");
        getJsonObject(URL_Route);

    }
    private void getJsonObject(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    //jsonArray
                    JSONArray train = response.getJSONArray("route");
                    for (int i=0; i<train.length();++i){
                        //get JSON object
                        TrainStation parseStation = new TrainStation();
                        JSONObject trainStationParse = train.getJSONObject(i).getJSONObject("station");
                        parseStation.setStationName(trainStationParse.getString("name"));
                        parseStation.setStationCode(trainStationParse.getString("code"));
//                        String fetchCode = trainStationParse.getString("code");
//                        String fetchName = trainStationParse.getString("name");

//                        if (nameDest.equals(fetchCode)){
//                            marker.setImageDrawable(getDrawable(R.drawable.positionmarker));
////                           parseStation.setStationCode(trainStationParse.getString("code"));
////                            parseStation.setStationName(trainStationParse.getString("name"));
//                        }
                        parseStation.setSrcNameMarker(srcMark);
                        parseStation.setMainCode(codeDest);
                        trainStations.add(parseStation);
                    }
                    recyclerViewadapter = new RouteRecyclerAdapter(trainStations, getApplicationContext());
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
}
