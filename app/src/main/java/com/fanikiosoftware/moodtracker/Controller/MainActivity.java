package com.fanikiosoftware.moodtracker.Controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.fanikiosoftware.moodtracker.R;

public class MainActivity extends AppCompatActivity implements OnTouchListener,
        GestureDetector.OnGestureListener {

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int BANANA_YELLOW = -398257;
    public static final int LIGHT_SAGE = -4658810;
    public static final int FADED_RED = -2212784;
    public static final int WARM_GREY = -6579301;
    public static final int CORNFLOWER_BLUE = -16537100;

    private ImageView ivMood;
    private EditText editText;
    private Button btnCancel, btnConfirm;
    public static final String PREF_KEY_MEMO = "PREFERENCE_KEY_MEMO";
    public static final String PREF_KEY_MOOD = "PREFERENCE_KEY_MOOD";
    private View view;
    private SharedPreferences mPreferences;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //checkNewUser();
        //set default bg color for first play
        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.faded_red);
        setContentView(R.layout.activity_main);
        ivMood = findViewById(R.id.ivMood);
        ivMood.setImageResource(R.drawable.sad_mood);
        ImageButton addButton = findViewById(R.id.btnAdd);
        ImageButton historyButton = findViewById(R.id.btnHistory);
        //setup button listeners
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mGestureDetector = new GestureDetector(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialogBuilder =
                        new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.popup_input_dialog, null);
                editText = dialogView.findViewById(R.id.etComment);
                btnConfirm = dialogView.findViewById(R.id.btnConfirm);
                btnCancel = dialogView.findViewById(R.id.btnCancel);
                //set up listeners
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        saveComment();
                        dialogBuilder.dismiss();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });
                dialogBuilder.setView(dialogView);
                dialogBuilder.show();
            }
        });
        //history btn listener, start History Activity
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(MainActivity.this,
                        HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });
    }

    //save memo from popup input dialog when user clicks on CONFIRM
    public void saveComment() {
        String comment = editText.getText().toString().trim();
        mPreferences.edit().putString(PREF_KEY_MEMO, comment).apply();
        System.out.println("PREF_KEY_MEMO: " + comment);
        int mood = ((ColorDrawable) view.getBackground()).getColor();
        mPreferences.edit().putInt(PREF_KEY_MOOD,mood);
        System.out.println("PREF_KEY_MOOD: " + mood);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent,
                           float velocityX, float velocityY) {
        //did event get handled here? Assume the negative
        boolean result = false;
        //gauge movement along y axis for determining if user swiped vertically
        float diffY = moveEvent.getY() - downEvent.getY();
        //ignore all non-vertical swipe gestures
        //handle vertical swipes
        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
            int color = ((ColorDrawable) view.getBackground()).getColor();
            if (diffY > 0) {
                onSwipeBottom(color);
            } else {
                onSwipeTop(color);
            }
            //gesture was handled within this method so result changed to TRUE
            result = true;
        }
        return result;
    }

    private void onSwipeBottom(int color) {
        switch (color) {
            case BANANA_YELLOW:
                view.setBackgroundResource(R.color.light_sage);
                ivMood.setImageResource(R.drawable.good_mood);
                break;
            case LIGHT_SAGE:
                view.setBackgroundResource(R.color.cornflower_blue);
                ivMood.setImageResource(R.drawable.decent_mood);
                break;
            case CORNFLOWER_BLUE:
                view.setBackgroundResource(R.color.warm_grey);
                ivMood.setImageResource(R.drawable.bad_mood);
                break;
            case WARM_GREY:
                view.setBackgroundResource(R.color.faded_red);
                ivMood.setImageResource(R.drawable.sad_mood);
                break;
            case FADED_RED:
                //do nothing - user must swipe upwards to change back to previous mood
                break;
        }
    }

    public void onSwipeTop(int color) {
        switch (color) {
            case FADED_RED:
                view.setBackgroundResource(R.color.warm_grey);
                ivMood.setImageResource(R.drawable.bad_mood);
                break;
            case WARM_GREY:
                view.setBackgroundResource(R.color.cornflower_blue);
                ivMood.setImageResource(R.drawable.decent_mood);
                break;
            case CORNFLOWER_BLUE:
                view.setBackgroundResource(R.color.light_sage);
                ivMood.setImageResource(R.drawable.good_mood);
                break;
            case LIGHT_SAGE:
                view.setBackgroundResource(R.color.banana_yellow);
                ivMood.setImageResource(R.drawable.great_mood);
                break;
            case BANANA_YELLOW:
                //do nothing- user must swipe up to go back through moods
                break;
        }
    }

    public void onSwipeRight() {
        // do nothing - ignore
    }

    public void onSwipeLeft() {
        // do nothing -ignore
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }
}