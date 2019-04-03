package com.parreira.proj1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parreira.proj1.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<String> mArray;

    public MyAdapter (Context context, ArrayList<String> array){
        this.mContext = context;
        this.mArray = array;
    }

    @Override
    public int getCount() {
        int size = 0;

        if(mArray != null){
            size = mArray.size();
        }

        return size;
    }

    @Override
    public Object getItem(int position) {
        String text = null;

        if (mArray.get(position) != null) {
            text = mArray.get(position);
        }
        return text;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.list_view, null);

        TextView mTextView = (TextView) convertView.findViewById(R.id.tv_list_view);

        mTextView.setText(mArray.get(position));
        return convertView;
    }
}