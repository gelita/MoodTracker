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

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity implements OnTouchListener,
        GestureDetector.OnGestureListener {

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int BANANA_YELLOW = -398257;
    public static final int LIGHT_SAGE = -4658810;
    public static final int CORNFLOWER_BLUE = -16537100;
    public static final int WARM_GREY = -6579301;
    public static final int FADED_RED = -2212784;

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
        view = this.getWindow().getDecorView();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        checkMood();
        ImageButton addButton = findViewById(R.id.btnAdd);
        ImageButton historyButton = findViewById(R.id.btnHistory);
        //set up listeners
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
                        saveMood();
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

    private void checkMood() {
        setContentView(R.layout.activity_main);
        ivMood = findViewById(R.id.ivMood);
        int mood = mPreferences.getInt(PREF_KEY_MOOD, 0);
        //if a mood has been stored, display,
        //else, default mood
        if (mood < 0) {
            switch (mood) {
                case BANANA_YELLOW:
                    ivMood.setImageResource(R.drawable.great_mood);
                    view.setBackgroundResource(R.color.banana_yellow);
                    break;
                case LIGHT_SAGE:
                    ivMood.setImageResource(R.drawable.good_mood);
                    view.setBackgroundResource(R.color.light_sage);
                    break;
                case CORNFLOWER_BLUE:
                    ivMood.setImageResource(R.drawable.decent_mood);
                    view.setBackgroundResource(R.color.cornflower_blue);
                    break;
                case WARM_GREY:
                    ivMood.setImageResource(R.drawable.bad_mood);
                    view.setBackgroundResource(R.color.warm_grey);
                    break;
                case FADED_RED:
                    ivMood.setImageResource(R.drawable.sad_mood);
                    view.setBackgroundResource(R.color.faded_red);
                    break;
            }
        } else {
            //set default mood and color if no mood was previously saved
            ivMood.setImageResource(R.drawable.great_mood);
            view.setBackgroundResource(R.color.banana_yellow);
        }
    }

    //save memo from popup input dialog when user clicks on CONFIRM
    public void saveComment() {
        mPreferences.edit().putString(PREF_KEY_MEMO, editText.getText().toString().trim()).apply();
    }

    //save the mood that is currently selected
    public void saveMood(){
        mPreferences.edit().putInt(PREF_KEY_MOOD,
                ((ColorDrawable) view.getBackground()).getColor()).apply();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.performClick();
        return false;
    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent,
                           float velocityX, float velocityY) {
        //did event get handled here? Assume the negative
        boolean result = false;
        //gauge movement along y axis for determining if user swiped vertically
        //subtract the starting position on the Y axis from the ending position on Y axis
        float diffY = moveEvent.getY() - downEvent.getY();
        //ignore all non-vertical swipe gestures
        //handle vertical swipes
        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
            int color = ((ColorDrawable) view.getBackground()).getColor();
            if (diffY > 0) {
                onSwipeDownwards(color);
            } else {
                onSwipeUpwards(color);
            }
            //gesture was handled within this method so result changed to TRUE
            result = true;
        }
        return result;
    }

    //mood changes from bad to good as user swipes upward
    private void onSwipeUpwards(int color) {
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
                //do nothing-user must swipe down to go back to lesser mood
                break;
        }
    }

    //mood changes from good to bad as user swipes downward
    public void onSwipeDownwards(int color) {
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
                //do nothing -user must swipe up to go back to better mood
                break;
        }
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

    @Override
    protected void onStart() {
        super.onStart();
        out.println("MainActivity::onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        out.println("MainActivity::onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveMood();
        out.println("MainActivity::onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        out.println("MainActivity::onResume()");
    }
}