package com.fanikiosoftware.moodtracker.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fanikiosoftware.moodtracker.Model.ModelClass;
import com.fanikiosoftware.moodtracker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> modelClassList;

    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        int height = parent.getMeasuredHeight()/7;
        int width = parent.getMeasuredWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        int resource = modelClassList.get(position).getMoodId();
        String title = modelClassList.get(position).getMemo();
        viewHolder.setData(resource, title);
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton imageButton;
        private TextView title;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.btnImage);
            title = itemView.findViewById(R.id.tvTitle);
        }

        private void setData(int resource, String titleText) {
            title.setText(titleText);
            imageButton.setImageResource(resource);
        }
    }
}