package dev452.app.trainapp.Controller.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dev452.app.trainapp.Controller.ClassItems.TrainStation;
import dev452.app.trainapp.R;

public class RouteRecyclerAdapter extends RecyclerView.Adapter<RouteRecyclerAdapter.MyViewHolder> {
    Context context;
    List<TrainStation> Station;
    public String destCode;
    public String srcCode;
    public String matchCode;
    public String matchSrc;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameStn;
        public TextView codeStn;
        public MyViewHolder(View itemView) {
            super(itemView);
            nameStn = itemView.findViewById(R.id.stationName);
            codeStn = itemView.findViewById(R.id.stationCode);
        }
    }

    public RouteRecyclerAdapter(List<TrainStation> Stn, Context context){
        super();
        this.Station = Stn;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainroutelist, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TrainStation trainStation = Station.get(position);
         matchCode = trainStation.getStationCode();
         matchSrc = trainStation.getStationCode();
         holder.nameStn.setText(trainStation.getStationName() + " -");
         holder.codeStn.setText(trainStation.getStationCode());
//            holder.codeStn.setTextColor(ContextCompat.getColor(context, R.color.light_blue_A700));
//            holder.nameStn.setTextColor(ContextCompat.getColor(context, R.color.light_blue_A700));


        destCode = trainStation.getMainCode();
        srcCode = trainStation.getSrcNameMarker();
        if(position % 2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#E1E2E1"));
        }
        if (trainStation.getStationCode().matches(srcCode)){
            holder.codeStn.setTextColor(ContextCompat.getColor(context, R.color.light_blue_A700));
            holder.nameStn.setTextColor(ContextCompat.getColor(context, R.color.light_blue_A700));
        }else if (trainStation.getStationCode().matches(destCode)){
            holder.codeStn.setTextColor(ContextCompat.getColor(context, R.color.light_blue_A700));
            holder.nameStn.setTextColor(ContextCompat.getColor(context, R.color.light_blue_A700));
        } else {
            holder.codeStn.setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.nameStn.setTextColor(ContextCompat.getColor(context, R.color.black));
        }


//        Log.d("Message: ",text);
   //     holder.codeStn.setTextColor(ContextCompat.getColor(context, R.color.light_blue_A700));
    //    holder.nameStn.setTextColor(ContextCompat.getColor(context, R.color.light_blue_A700));
    }

    @Override
    public int getItemCount() {
        return Station.size();
    }

}
