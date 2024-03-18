package com.example.codingplayground;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Integer> {

    private ArrayList<Integer> arrayList;
    private Context context;

    public CustomAdapter(Context context, int layoutId, ArrayList<Integer> dataList){
        super(context, layoutId, dataList);
        this.arrayList = dataList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Integer result = arrayList.get(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).
                    inflate(android.R.layout.simple_list_item_1, parent, false);

        }

        TextView text = (TextView) convertView.findViewById(android.R.id.text1);
        if (result != null){
            text.setText("Result: " + result);

        }else {
            text.setText("Error");
        }

        return convertView;
    }


}
