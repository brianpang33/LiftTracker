package com.example.brian.lifttracker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;


public class CustomArrayAdapter extends ArrayAdapter {

    private Context context;
    private List<String> string;

    public CustomArrayAdapter(Context context, ArrayList list) {
        super(context, android.R.layout.activity_list_item, list);

        this.context = context;
        this.string = list;
    }

    @Override
    public int getCount(){
        return string.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        String sname = string.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.my_text_view,null);


        TextView name = view.findViewById(R.id.name_view);
        TextView weight = view.findViewById(R.id.weight_view);
        TextView sets = view.findViewById(R.id.sets_view);
        TextView reps = view.findViewById(R.id.reps_view);
        TextView comments = view.findViewById(R.id.comments_view);


        String[] s = sname.split(",");
        name.setText(s[0]);
        weight.setText(s[1]);
        sets.setText(s[2]);
        reps.setText(s[3]);

        if(s.length > 4) {
            comments.setText(sname.split(",")[4]);
        }else{
            comments.setText("");
        }


        return view;

    }




}
