package com.fanikiosoftware.moodtracker.Controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fanikiosoftware.moodtracker.Model.ModelClass;
import com.fanikiosoftware.moodtracker.R;
import com.fanikiosoftware.moodtracker.Utility.Constants;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryActivity extends AppCompatActivity {

    private static final String TAG = "HistoryActivity";

    ArrayList<ModelClass> list = new ArrayList<>();
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_history);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        getData();
        initRecyclerView();
    }
    public void getData(){
        int[] moodId = new int[7];
        String[] memo = new String[7];
        moodId[0] = mPreferences.getInt(Constants.PREF_KEY_MOOD7, 5);
        moodId[1] = mPreferences.getInt(Constants.PREF_KEY_MOOD6, 5);
        moodId[2] = mPreferences.getInt(Constants.PREF_KEY_MOOD5, 5);
        moodId[3] = mPreferences.getInt(Constants.PREF_KEY_MOOD4, 5);
        moodId[4] = mPreferences.getInt(Constants.PREF_KEY_MOOD3, 5);
        moodId[5] = mPreferences.getInt(Constants.PREF_KEY_MOOD2, 5);
        moodId[6] = mPreferences.getInt(Constants.PREF_KEY_MOOD1, 5);

        memo[0] = mPreferences.getString(Constants.PREF_KEY_MEMO7, "");
        memo[1] = mPreferences.getString(Constants.PREF_KEY_MEMO6, "");
        memo[2] = mPreferences.getString(Constants.PREF_KEY_MEMO5, "");
        memo[3] = mPreferences.getString(Constants.PREF_KEY_MEMO4, "");
        memo[4] = mPreferences.getString(Constants.PREF_KEY_MEMO3, "");
        memo[5] = mPreferences.getString(Constants.PREF_KEY_MEMO2, "");
        memo[6] = mPreferences.getString(Constants.PREF_KEY_MEMO1, "");

        for (int i = 0 ; i <7; i++) {
            ModelClass modelClass = new ModelClass(moodId[i], memo[i]);
            list.add(i, modelClass);
        }
    }

//  initialize recyclerView and attach adapter / layout manager
    public void initRecyclerView(){
        Log.d(TAG, "initRecyclerView:: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}