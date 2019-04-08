package com.parreira.proj1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parreira.proj1.R;
import com.parreira.proj1.activity.MainActivity;
import com.parreira.proj1.activity.Pessoa;
import com.parreira.proj1.activity.SecondActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static android.support.v4.content.ContextCompat.startActivity;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder item);
    }

    private static Context mContext;
    private List<Pessoa> mArray;
    private OnItemClickListener monItemClickListener;

    public MyAdapter(Context context, List<Pessoa> array) {
        this.mContext = context;
        this.mArray = array;
    }

    public static abstract class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View listView;
        TextView mTextView;
        TextView mTextView2;
        CircleImageView profileImage;
        CardView parentLayout;


        public MyViewHolder(final View v) {
            super(v);
            //listView = v;


            mTextView = (TextView) v.findViewById(R.id.tv_name);
            mTextView2 = (TextView) v.findViewById(R.id.tv_resume);
            profileImage = (CircleImageView) v.findViewById(R.id.img_profile_image);
            parentLayout = (CardView) v.findViewById(R.id.cv_card_view);

            //listView.setOnClickListener(this);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Pessoa pessoa = (Pessoa) v.getTag();
                    Log.d("Debug", "está a clicar " + pessoa.getNome());


                    Intent intent = new Intent(MyAdapter.mContext, SecondActivity.class);
                    intent.putExtra(SecondActivity.KEY_PESSOA, pessoa);
                    MyAdapter.mContext.startActivity(intent);

                }
            });

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {


        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View contextView = mInflater.inflate(R.layout.list_view, null);

        MyViewHolder vh = new MyViewHolder(contextView) {
            @Override
            public void onClick(View v) {

            }
        };
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TextView mTextView = (TextView) holder.itemView.findViewById(R.id.tv_name);
        TextView mTextView2 = (TextView) holder.itemView.findViewById(R.id.tv_resume);
        CircleImageView profileImage = (CircleImageView) holder.itemView.findViewById(R.id.img_profile_image);


        mTextView.setText(mArray.get(position).getNome());
        mTextView2.setText(mArray.get(position).getTexto());

        int img = mArray.get(position).getImage();
        if (img > 0) {
            profileImage.setImageResource(img);
        } else {
            profileImage.setImageResource(R.drawable.ic_launcher_foreground);
        }

        holder.itemView.setTag(mArray.get(position));
    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }
}