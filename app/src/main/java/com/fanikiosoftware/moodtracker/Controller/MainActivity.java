package com.fanikiosoftware.moodtracker.Controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.fanikiosoftware.moodtracker.R;

public class MainActivity extends AppCompatActivity implements OnTouchListener, GestureDetector.OnGestureListener {

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private ImageView ivMood;
    private ImageButton mAddButton;
    private ImageButton mHistoryButton;
    private EditText editText;
    private Button btnCancel, btnConfirm;
    public static final String PREF_KEY_MEMO1 = "PREFERENCE_KEY_MEMO1";

    private SharedPreferences mPreferences;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //checkNewUser();
        //set default color background for first play
        setActivityBackgroundColor(R.color.banana_yellow);
        setContentView(R.layout.activity_main);
        ivMood = findViewById(R.id.ivMood);
        ivMood.setImageResource(R.drawable.great_mood);
        mAddButton = findViewById(R.id.btnAdd);
        mHistoryButton = findViewById(R.id.btnHistory);
        //setup button listeners
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mGestureDetector = new GestureDetector(this);
        mAddButton.setOnClickListener(new View.OnClickListener() {
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
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(MainActivity.this,
                        HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });
    }

    //save memo from popup input dialog
    public void saveComment() {
        String comment = editText.getText().toString().trim();
        mPreferences.edit().putString(PREF_KEY_MEMO1, comment).apply();
        System.out.println("PREF_KEY_MEMO1: " + comment);
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
    //change bg color of Activity
    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundResource(color);
    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        //did event get handled here ? assume the negative
        boolean result = false;
        //greater mov't along x or y axis? This determines horiz. mov't vs vertical mov't
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();
        //which axis had the greater movement? X or Y
        if (Math.abs(diffX) > Math.abs(diffY)) {
            //right or left swipe
            if (Math.abs(diffX) > SWIPE_THRESHOLD &&
                    Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
                result = true;
            }
        } else {
            //vertical swipe
            if (Math.abs(diffY) > SWIPE_THRESHOLD
                    && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
                result = true;
            }
        }
        return result;
    }

    private void onSwipeBottom() {
        Toast.makeText(this, "swipe bottom", Toast.LENGTH_SHORT).show();
    }

    public void onSwipeTop() {
        Toast.makeText(this, "swipe top", Toast.LENGTH_SHORT).show();
    }

    public void onSwipeRight() {
        Toast.makeText(this, "swipe right", Toast.LENGTH_SHORT).show();
    }

    public void onSwipeLeft() {
        Toast.makeText(this, "swipe left", Toast.LENGTH_SHORT).show();
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