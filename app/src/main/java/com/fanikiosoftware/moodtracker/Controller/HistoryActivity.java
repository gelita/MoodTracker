package com.fanikiosoftware.moodtracker.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fanikiosoftware.moodtracker.Model.ModelClass;
import com.fanikiosoftware.moodtracker.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryActivity extends AppCompatActivity {

    private static final String TAG = "HistoryActivity";

    ArrayList<ModelClass> modelClassList = new ArrayList<>();
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_history);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int[] moodId = new int[7];
        String[] memo = new String[7];
        int i;
        for(i = 0; i < 7; i++) {
               moodId[i] = mPreferences.getInt("PREF_KEY_MOOD" + i+1, -1);
               memo[i] = mPreferences.getString("PREF_KEY_MEMO" + i+1, null);
        }
        modelClassList.add(new ModelClass(moodId[6], memo[6]));
        modelClassList.add(new ModelClass(moodId[5], memo[5]));
        modelClassList.add(new ModelClass(moodId[4], memo[4]));
        modelClassList.add(new ModelClass(moodId[3], memo[3]));
        modelClassList.add(new ModelClass(moodId[2], memo[2]));
        modelClassList.add(new ModelClass(moodId[1], memo[1]));
        modelClassList.add(new ModelClass(moodId[0], memo[0]));
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, modelClassList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
