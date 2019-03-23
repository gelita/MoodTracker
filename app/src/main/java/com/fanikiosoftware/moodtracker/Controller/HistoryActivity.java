package com.fanikiosoftware.moodtracker.Controller;

import android.os.Bundle;

import com.fanikiosoftware.moodtracker.Model.ModelClass;
import com.fanikiosoftware.moodtracker.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryActivity extends AppCompatActivity {

    //message buttons by day btn1=1day ago..btn7=week ago
    //private Button btnMemo1,btnMemo2,btnMemo3,btnMemo4,btnMemo5,btnMemo6,btnMemo7;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
//        setActivityBackgroundColor(R.color.light_grey);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        List<ModelClass> modelClassList = new ArrayList<>();
        modelClassList.add(new ModelClass(R.drawable.comment, getString(R.string.title_7)));
        modelClassList.add(new ModelClass(R.drawable.comment, getString(R.string.title_6)));
        modelClassList.add(new ModelClass(R.drawable.comment, getString(R.string.title_5)));
        modelClassList.add(new ModelClass(R.drawable.comment, getString(R.string.title_4)));
        modelClassList.add(new ModelClass(R.drawable.comment, getString(R.string.title_3)));
        modelClassList.add(new ModelClass(R.drawable.comment, getString(R.string.title_2)));
        modelClassList.add(new ModelClass(R.drawable.comment, getString(R.string.title_1)));
        Adapter adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

//    public void setActivityBackgroundColor(int color) {
//        View view = this.getWindow().getDecorView();
//        view.setBackgroundResource(color);
//    }
}
