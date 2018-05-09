//package dev452.app.trainapp.Controller.Adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import dev452.app.trainapp.Controller.ClassItems.SuggestionClass;
//import dev452.app.trainapp.R;
//
//public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.SuggestViewHolder>{
//     Context context;
//     List<SuggestionClass> suggestionAdapter;
//
//
//    public SuggestionAdapter(List<SuggestionClass> suggestionAdapter,Context context){
//        this.suggestionAdapter = suggestionAdapter;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public SuggestionAdapter.SuggestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestionlist, parent, false);
//        SuggestViewHolder suggestViewHolder = new SuggestViewHolder(v);
//        return suggestViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SuggestionAdapter.SuggestViewHolder holder, int position) {
//        SuggestionClass suggestionClass = suggestionAdapter.get(position);
//        holder.sourceSuggestion.setText(suggestionClass.getSourceSuggestion());
//        holder.codeSuggestion.setText(suggestionClass.getCodeSuggestion());
//    }
//    @Override
//    public int getItemCount() {
//        return suggestionAdapter.size();
//    }
//
//    public void updateList(List<SuggestionClass> list){
//        suggestionAdapter = list;
//        notifyDataSetChanged();
//    }
//
//    public class SuggestViewHolder extends RecyclerView.ViewHolder {
//        public TextView sourceSuggestion;
//        public TextView codeSuggestion;
//        public SuggestViewHolder(View itemView) {
//            super(itemView);
//            sourceSuggestion = itemView.findViewById(R.id.sourceSuggest);
//            codeSuggestion = itemView.findViewById(R.id.codeSuggestion);
//        }
//    }
//}
