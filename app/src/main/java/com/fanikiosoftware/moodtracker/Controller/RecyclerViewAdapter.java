package com.fanikiosoftware.moodtracker.Controller;

import android.content.Context;
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
        Log.d(TAG, "onCreateViewHolder: starting");
        int height = parent.getMeasuredHeight() / 7;
        //??
        int width = parent.getMeasuredWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
//      create new ViewHolder object and pass the view to it
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder called");
        holder.title.setText(Constants.titles[position]);
        holder.parentLayout.setBackgroundColor(Constants.colorsArr[1]);
        String memo = " ";
        holder.btnImage.setImageResource(R.drawable.comment);
        if ((memo != null) || (!memo.isEmpty())) {
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "clicked on image: " + Constants.titles[position]);
                    Toast.makeText(mContext, "This is the toast " +
                            Constants.titles[position], Toast.LENGTH_SHORT).show();
                }
            });
        }else{
//          if there is no memo, hide memo button
            holder.btnImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        System.out.println("titles length: " + Constants.titles.length);
        return Constants.titles.length;
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