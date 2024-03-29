package com.fanikiosoftware.moodtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fanikiosoftware.moodtracker.model.ModelClass;
import com.fanikiosoftware.moodtracker.R;
import com.fanikiosoftware.moodtracker.utility.Constants;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<ModelClass> modelClass;
    private Context mContext;
    private int height;
    private int width;

    public RecyclerViewAdapter(Context mContext, ArrayList<ModelClass> modelClass) {
        this.modelClass = modelClass;
        this.mContext = mContext;
    }

    //    method responsible for inflating the layout view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        height = parent.getMeasuredHeight() / 7;
        width = parent.getMeasuredWidth();
//      create new ViewHolder object and pass the view to it
        return new ViewHolder(view);
    }

    //    this method recycles the view using the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        int moodId = modelClass.get(position).getMoodId();
        String memo = modelClass.get(position).getMemo();
        holder.itemView.setBackgroundColor(Color.parseColor(Constants.colorsArr[moodId]));
        holder.title.setText(Constants.titles[position]);
//      if memo exists, show comment button, share button AND set onClickListener
        if (!memo.isEmpty()) {
            holder.btnImage.setVisibility(View.VISIBLE);
            holder.btnImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, modelClass.get(position).getMemo(), Toast.LENGTH_LONG).show();
                }
            });
        }
//      show share button & set onClickListener if moodId is one that was saved & not default grey
        if (moodId <= 4) {
            System.out.println("moodId is now: " + moodId);
            holder.btnShare.setVisibility(View.VISIBLE);
            holder.btnShare.setBackground(Drawable.createFromPath(Constants.colorsArr[moodId]));
            holder.btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String mood = Constants.moods[modelClass.get(position).getMoodId()];
                    String shareBody = Constants.titles[position]
                            + " my mood was " + mood + ".";
                    String shareSubject = "Your subject here";
                    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSubject);
                    intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    mContext.startActivity(Intent.createChooser(intent, "Share using"));
                }
            });
        }
        ViewGroup.LayoutParams layoutParams = holder.container.getLayoutParams();
        layoutParams.height = height;
        float multiplier;
        switch (moodId) {
            case 1:
                multiplier = .8f;
                break;
            case 2:
                multiplier = .6f;
                break;
            case 3:
                multiplier = .4f;
                break;
            case 4:
                multiplier = .2f;
                break;
            default:
                multiplier = 1f;
                break;
        }
        layoutParams.width = (int) (width * multiplier);
        holder.container.setLayoutParams(layoutParams);
    }

    //  get the number of items of type modelClass to display in recyclerView
    @Override
    public int getItemCount() {
        return Constants.titles.length;
    }

    //**********        ViewHolder Class      *********************
    class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "ViewHolder";
        private ImageButton btnShare;
        private TextView title;
        private ImageButton btnImage;
        private View container;

        ViewHolder(@NonNull View view) {
            super(view);
            container = view.findViewById(R.id.parent_layout);
            title = view.findViewById(R.id.tvTitle);
            btnImage = view.findViewById(R.id.btnImage);
            btnShare = view.findViewById(R.id.btnShare);
        }
    }
}