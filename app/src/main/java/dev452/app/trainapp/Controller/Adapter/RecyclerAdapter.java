package dev452.app.trainapp.Controller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import dev452.app.trainapp.Activities.TrainRouteActivity;
import dev452.app.trainapp.Controller.ClassItems.Train;
import dev452.app.trainapp.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    List<Train> getDataAdapter;

    public RecyclerAdapter(List<Train> getDataAdapter, Context context){
        super();

        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        Typeface typeface = Typeface.createFromAsset(viewHolder.itemView.getContext().getAssets(),"fonts/helvetica.ttf");
        Typeface typeface_bold = Typeface.createFromAsset(viewHolder.itemView.getContext().getAssets(),"fonts/helvetica_bold.ttf");
        viewHolder.source.setTypeface(typeface_bold);
        viewHolder.destination.setTypeface(typeface_bold);
        viewHolder.srcTime.setTypeface(typeface);
        viewHolder.destTime.setTypeface(typeface);
        viewHolder.trainName.setTypeface(typeface_bold);
        viewHolder.trainNo.setTypeface(typeface_bold);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Train getDataAdapter1 =  getDataAdapter.get(position);

        holder.source.setText(getDataAdapter1.getSrcName());

        holder.destination.setText(getDataAdapter1.getDestName());

        holder.srcTime.setText(getDataAdapter1.getSrcTime());

        holder.destTime.setText(getDataAdapter1.getDestTime());

        holder.trainName.setText("Train Name: " + getDataAdapter1.getTrainName());

        holder.trainNo.setText("Train No: " + getDataAdapter1.getTrainNumber());

        holder.srcName.setText(getDataAdapter1.getSourceName() + " - ");

        holder.destName.setText(getDataAdapter1.getDestinationName() + " - ");

//        if(position %2 == 1)
//        {
//            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        }
//        else
//        {
//            holder.itemView.setBackgroundColor(\n);
//            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
//        }

    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView source;
        public TextView destination;
        public TextView srcDate;
        public TextView destDate;
        public TextView srcTime;
        public TextView destTime;
        public TextView trainName;
        public TextView trainNo;
        public TextView srcName;
        public TextView destName;



        public ViewHolder(View itemView) {

            super(itemView);
            srcName = itemView.findViewById(R.id.srcName);
            destName = itemView.findViewById(R.id.destName);
            source = itemView.findViewById(R.id.source);
            destination = itemView.findViewById(R.id.destination);
            srcDate = itemView.findViewById(R.id.sourceDate);
            destDate = itemView.findViewById(R.id.destDate);
            srcTime = itemView.findViewById(R.id.srcTime);
            destTime = itemView.findViewById(R.id.destTime);
            trainName = itemView.findViewById(R.id.trainName);
            trainNo = itemView.findViewById(R.id.trainNumber);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Train trainData = getDataAdapter.get(position);
            Intent intent = new Intent(context, TrainRouteActivity.class);
            intent.putExtra("trainNo",trainData.getTrainNumber());
            intent.putExtra("codeDest",trainData.getDestName());
            intent.putExtra("nameDest",trainData.getDestinationName());
            intent.putExtra("nameSrc",trainData.getSrcName());
            context.startActivity(intent);
//            Intent routeIntent = new Intent(context,RouteRecyclerAdapter.class);
//            routeIntent.putExtra("codeDest",trainData.getDestName());
//            context.startActivity(routeIntent);
//            Toast.makeText(context,trainData.getTrainName(),Toast.LENGTH_LONG).show();
        }
    }
}
