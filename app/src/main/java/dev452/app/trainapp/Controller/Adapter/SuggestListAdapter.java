package dev452.app.trainapp.Controller.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dev452.app.trainapp.Controller.ClassItems.SuggestionClass;
import dev452.app.trainapp.R;

public class SuggestListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<SuggestionClass> suggestionClassesList;
    public SuggestListAdapter(Activity activity, List<SuggestionClass> suggestionClassArrayAdapter){
        this.activity = activity;
        this.suggestionClassesList = suggestionClassArrayAdapter;
    }
    @Override
    public int getCount() {
        return suggestionClassesList.size();
    }

    @Override
    public Object getItem(int i) {
        return suggestionClassesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.suggestionlist, null);
        TextView source = convertView.findViewById(R.id.sourceSuggest);
        TextView code = convertView.findViewById(R.id.codeSuggestion);
        SuggestionClass suggestionClass = suggestionClassesList.get(i);
        source.setText(suggestionClass.getSourceSuggestion());
        code.setText(suggestionClass.getCodeSuggestion());
        return convertView;
    }
}
