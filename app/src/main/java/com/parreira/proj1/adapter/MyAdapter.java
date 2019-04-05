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
import com.parreira.proj1.activity.Pessoa;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends BaseAdapter{

    private Context mContext;
    private List<Pessoa> mArray;

    public MyAdapter (Context context, List<Pessoa> array){
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
        if (mArray.get(position) != null) {
            return mArray.get(position);
        }
        else{
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View contextView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        contextView = mInflater.inflate(R.layout.list_view, null);

        TextView mTextView = (TextView) contextView.findViewById(R.id.tv_name);
        TextView mTextView2 = (TextView) contextView.findViewById(R.id.tv_resume);
        CircleImageView profileImage = (CircleImageView) contextView.findViewById(R.id.img_profile_image);

        mTextView.setText(mArray.get(position).getNome());
        mTextView2.setText(mArray.get(position).getTexto());

        int img = mArray.get(position).getImage();
        if(img > 0){
            profileImage.setImageResource(img);
        } else{
            profileImage.setImageResource(R.drawable.ic_launcher_foreground);
        }

        return contextView;
    }
}