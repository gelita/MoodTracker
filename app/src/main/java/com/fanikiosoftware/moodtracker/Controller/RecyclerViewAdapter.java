package com.fanikiosoftware.moodtracker.Controller;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fanikiosoftware.moodtracker.Model.ModelClass;
import com.fanikiosoftware.moodtracker.R;
import com.fanikiosoftware.moodtracker.Utility.Constants;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<ModelClass> modelClass;
    private Context mContext;
    private String memo;
    private int moodId;

    public RecyclerViewAdapter(Context mContext, ArrayList<ModelClass> modelClass) {
        this.modelClass = modelClass;
        this.mContext = mContext;
    }

    //    method responsible for inflating the layout view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        Log.d(TAG, "starting");
        int height = parent.getMeasuredHeight() / 7;
        int width = parent.getMeasuredWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
//      create new ViewHolder object and pass the view to it
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    //this method recycles the view using the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder started");
        Log.d(TAG, "position:: " + position);//position->0-6
        moodId = modelClass.get(position).getMoodId();
        memo = modelClass.get(position).getMemo();
        Log.d(TAG, "moodId::" + moodId + "  memo::" + memo + "."); //moodId-> 0-5
        holder.itemView.setBackgroundColor(Color.parseColor(Constants.colorsArr[moodId]));
        holder.title.setText(Constants.titles[position]);
//        if memo exists then show comment button AND set onClickListener
        if (!memo.isEmpty()){
            holder.btnImage.setVisibility(View.VISIBLE);
            holder.btnImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "clicked on image from " + Constants.titles[position]);
                    Toast.makeText(mContext, modelClass.get(position).getMemo(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

        @Override
        public int getItemCount () {
            return Constants.titles.length; //7
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private static final String TAG = "ViewHolder";
            private RelativeLayout parentLayout;
            private TextView title;
            private ImageButton btnImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                Log.d(TAG, "ViewHolder: setting up views");
                parentLayout = itemView.findViewById(R.id.parent_layout);
                title = itemView.findViewById(R.id.tvTitle);
                btnImage = itemView.findViewById(R.id.btnImage);
            }
        }
    }