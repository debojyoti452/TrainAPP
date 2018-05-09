package dev452.app.trainapp.Activities;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dev452.app.trainapp.Controller.Adapter.RecyclerAdapter;
import dev452.app.trainapp.Controller.Adapter.SuggestListAdapter;
import dev452.app.trainapp.Controller.ApiKeyClass;
import dev452.app.trainapp.Controller.AppController;
import dev452.app.trainapp.Controller.ClassItems.RecentHistory;
import dev452.app.trainapp.Controller.Adapter.RecentRecyclerAcitivity;
import dev452.app.trainapp.Controller.ClassItems.SuggestionClass;
import dev452.app.trainapp.Controller.ClassItems.Train;
import dev452.app.trainapp.libs.MyBounceInterpolator;
import dev452.app.trainapp.R;
import dev452.app.trainapp.libs.MyDividerItemDecoration;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public String URL;
    private AppCompatButton searchButton;
    public int Year;
    public int Month;
    public int Day;
    Calendar calendar;
    public String date;
    DatePickerDialog datePickerDialog;
    TextView datePicker;
    List<RecentHistory> list = new ArrayList<>();
    RecyclerView recyclerView;
//    RecyclerView suggestRecyclerView;
//    LinearLayoutManager sLayoutManager;
    RecentRecyclerAcitivity adapter;
    LinearLayoutManager mLayoutManager;
    private RequestQueue queue;
    private final String RECYCLER_POSITION_KEY = "recycler_position";
    private int mPosition = RecyclerView.NO_POSITION;
    private Bundle mBundleState;
    private List<SuggestionClass> getSuggestionList = new ArrayList<>();
    private ListView listView;
    RecyclerView.Adapter recyclerViewadapter;
    public EditText source;
    public EditText destination;
    String src,dest,text;
    private SuggestListAdapter suggestListAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        source = findViewById(R.id.source);
        destination = findViewById(R.id.destination);
        searchButton = findViewById(R.id.searchBtn);
        listView = findViewById(R.id.suggestListView);
        suggestListAdapter = new SuggestListAdapter(this, getSuggestionList);
        listView.setAdapter(suggestListAdapter);
        queue = Volley.newRequestQueue(this);
//        suggest Rview
//        suggestRecyclerView = findViewById(R.id.suggestRecyclerView);
//        sLayoutManager = new LinearLayoutManager(getApplicationContext());
//        sLayoutManager.setReverseLayout(true);
//        sLayoutManager.setStackFromEnd(true);
//        suggestRecyclerView.addItemDecoration(new MyDividerItemDecoration(this,LinearLayoutManager.VERTICAL, 16));
//        suggestRecyclerView.setLayoutManager(sLayoutManager);
//
//        recent RView

        recyclerView = findViewById(R.id.recentRecyclerView);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setLayoutManager(mLayoutManager);


//      recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
//        datePicker.setText(currentDate);
//        new DrawerBuilder().withActivity(this).build();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                src = source.getEditableText().toString().toUpperCase().trim();
                dest = destination.getEditableText().toString().toUpperCase().trim();
                if (src.matches("") || dest.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please type station name", Toast.LENGTH_LONG).show();
//                   URL = String.format("https://api.railwayapi.com/v2/suggest-station/name/"+src+"/apikey/"+ApiKeyClass.API_KEY+"/");
//                   getJsonObject(URL);
                } else {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("src", src);
                    intent.putExtra("dest", dest);
//                    intent.putExtra("date", currentDate);
                    RecentHistory userData = new RecentHistory();
                    userData.setRecentSrc(src);
                    userData.setRecentDest(dest);
                    list.add(userData);
                    startActivity(intent);
//                URL = String.format("https://api.railwayapi.com/v2/suggest-station/name/"+src+"/apikey/"+ApiKeyClass.API_KEY+"/");
//                adapter = new RecentRecyclerAcitivity(list,getApplicationContext());
//                recyclerView.setAdapter(adapter);
                }
            }
        });
//        source.addTextChangedListener(new TextWatcher() {
//            String text = source.getText().toString();
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                listView.setVisibility(View.VISIBLE);
//                for (i = 1;i<charSequence.toString().length();++i){
//                    if(charSequence.toString().length() == i){
//                        URL = String.format("https://api.railwayapi.com/v2/suggest-station/name/"+text+"/apikey/"+ApiKeyClass.API_KEY+"/");
//                        getJsonObject(URL);
//                    }else if (charSequence.toString().length() < i){
//                        URL = String.format("https://api.railwayapi.com/v2/suggest-station/name/"+text+"/apikey/"+ApiKeyClass.API_KEY+"/");
//                        getJsonObject(URL);
//                    }else if (charSequence.toString().length() > i){
//                        URL = String.format("https://api.railwayapi.com/v2/suggest-station/name/"+text+"/apikey/"+ApiKeyClass.API_KEY+"/");
//                        getJsonObject(URL);
//                    }else {
//                        URL = String.format("https://api.railwayapi.com/v2/suggest-station/name/"+text+"/apikey/"+ApiKeyClass.API_KEY+"/");
//                        getJsonObject(URL);
//                    }
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        
                datePicker = findViewById(R.id.datePicker);

                calendar = Calendar.getInstance();
                Year = calendar.get(Calendar.YEAR);
                Month = calendar.get(Calendar.MONTH);
                Day = calendar.get(Calendar.DAY_OF_MONTH);


                datePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        datePickerDialog = DatePickerDialog.newInstance(MainActivity.this, Year, Month, Day);

                        datePickerDialog.setThemeDark(true);

                        datePickerDialog.showYearPickerFirst(false);

                        datePickerDialog.setAccentColor(Color.parseColor("#009688"));

                        datePickerDialog.setTitle("Select Date From DatePickerDialog");

                        datePickerDialog.show(getFragmentManager(), "DatePickerDialog");


                    }
                });


    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    listView.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        // Save RecyclerView state
        mBundleState = new Bundle();
        mPosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
        mBundleState.putInt(RECYCLER_POSITION_KEY, mPosition);
    }
    @Override
    protected void onResume()
    {
        super.onResume();

        // Restore RecyclerView state
        if (mBundleState != null) {
            mPosition = mBundleState.getInt(RECYCLER_POSITION_KEY);
            if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
            // Scroll the RecyclerView to mPosition
            recyclerView.smoothScrollToPosition(mPosition);
        }
    }

    public void buttonClicked(View view) {

        ImageView button = findViewById(R.id.twistBtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        button.startAnimation(myAnim);
        EditText src = findViewById(R.id.source);
        EditText dest = findViewById(R.id.destination);
        String getFirstText = String.valueOf(src.getText());
        String getSecondText = String.valueOf(dest.getText());
        src.setText(getSecondText);
        dest.setText(getFirstText);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        //currentDate = dayOfMonth+"-"+monthOfYear+"-"+year;
        date = dayOfMonth + "/" + monthOfYear + "/" + year;
       // Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();
        datePicker.setText(date);
    }
    private void getJsonObject(String url){
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {

            @Override
           public void onResponse(JSONObject response) {
                try {
                    //jsonArray
                    JSONArray train = response.getJSONArray("stations");
                    for (int i=0; i<train.length();++i){
                        SuggestionClass suggestionClass = new SuggestionClass();
                        JSONObject code = train.getJSONObject(i);
                        JSONObject name = train.getJSONObject(i);
                        Log.d("Code:", code.getString("code"));
                        Log.d("Name:", name.getString("name"));
                        suggestionClass.setSourceSuggestion(name.getString("name"));
                        suggestionClass.setCodeSuggestion(code.getString("code"));
                        getSuggestionList.add(0,suggestionClass);
                    }
                    suggestListAdapter.notifyDataSetChanged();
//                    recyclerViewadapter = new SuggestionAdapter(getSuggestionList, getApplicationContext());
//                    suggestRecyclerView.setAdapter(recyclerViewadapter);
//                    recyclerViewadapter.notifyDataSetChanged();

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
        queue.add(jsonRequest);
    }
}