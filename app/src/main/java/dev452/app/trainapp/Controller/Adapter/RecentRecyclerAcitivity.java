package dev452.app.trainapp.Controller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dev452.app.trainapp.Activities.ResultActivity;
import dev452.app.trainapp.Controller.ClassItems.RecentHistory;
import dev452.app.trainapp.R;

public class RecentRecyclerAcitivity extends RecyclerView.Adapter<RecentRecyclerAcitivity.RecentViewHolder>{
    Context context;
    List<RecentHistory> getDataAdapter;

    public RecentRecyclerAcitivity(List<RecentHistory> getData, Context context){
        super();
        this.getDataAdapter = getData;
        this.context = context;
    }
    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recent_history, parent, false);

        RecentViewHolder viewHolder = new RecentRecyclerAcitivity.RecentViewHolder(view,context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, final int position) {
        RecentHistory getDataAdapter1 =  getDataAdapter.get(position);
        holder.recentSource.setText(getDataAdapter1.getRecentSrc());
        holder.recentDest.setText(getDataAdapter1.getRecentDest());
//        if(position % 2 == 1)
//        {
//            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        }
//        else
//        {
//            holder.itemView.setBackgroundColor(Color.parseColor("#E1E2E1"));
//        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataAdapter.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
    }



    @Override
    public int getItemCount() {
        return getDataAdapter.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class RecentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recentSource;
        public TextView recentDest;
        public ImageView delete;

        public RecentViewHolder(View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            recentSource = itemView.findViewById(R.id.recentSource);
            recentDest = itemView.findViewById(R.id.recentDest);
            delete = itemView.findViewById(R.id.deleteButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            RecentHistory trainData = getDataAdapter.get(position);
            Intent intent = new Intent(context, ResultActivity.class);
            intent.putExtra("src",trainData.getRecentSrc());
            intent.putExtra("dest",trainData.getRecentDest());
            context.startActivity(intent);
        }
    }

    public void UpdateData(int position,RecentHistory userData){
        getDataAdapter.remove(position);
        getDataAdapter.add(userData);
        notifyDataSetChanged();
    }

}
