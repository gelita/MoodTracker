package com.fanikiosoftware.moodtracker.Controller;import android.app.AlarmManager;import android.app.AlertDialog;import android.app.PendingIntent;import android.content.Context;import android.content.Intent;import android.content.SharedPreferences;import android.graphics.drawable.ColorDrawable;import android.os.Bundle;import android.preference.PreferenceManager;import android.view.GestureDetector;import android.view.LayoutInflater;import android.view.MotionEvent;import android.view.View;import android.view.View.OnTouchListener;import android.widget.Button;import android.widget.EditText;import android.widget.ImageButton;import android.widget.ImageView;import android.widget.Toast;import com.fanikiosoftware.moodtracker.R;import com.fanikiosoftware.moodtracker.Utility.Constants;import java.util.Calendar;import androidx.appcompat.app.AppCompatActivity;import static java.lang.System.out;public class MainActivity extends AppCompatActivity implements OnTouchListener,        GestureDetector.OnGestureListener {    public static final int SWIPE_THRESHOLD = 100;    public static final int SWIPE_VELOCITY_THRESHOLD = 100;    private ImageView ivMood;    private EditText editText;    private Button btnCancel, btnConfirm;    private View view;    private SharedPreferences mPreferences;    private GestureDetector mGestureDetector;    @Override    protected void onCreate(final Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        view = this.getWindow().getDecorView();        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);        checkMood();        setAlarm();        ImageButton addButton = findViewById(R.id.btnAdd);        ImageButton historyButton = findViewById(R.id.btnHistory);        //set up listeners        mGestureDetector = new GestureDetector(this);        addButton.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                final AlertDialog dialogBuilder =                        new AlertDialog.Builder(MainActivity.this).create();                LayoutInflater inflater = MainActivity.this.getLayoutInflater();                View dialogView = inflater.inflate(R.layout.popup_input_dialog, null);                editText = dialogView.findViewById(R.id.etComment);                btnConfirm = dialogView.findViewById(R.id.btnConfirm);                btnCancel = dialogView.findViewById(R.id.btnCancel);                //set up listeners                btnConfirm.setOnClickListener(new View.OnClickListener() {                    @Override                    public void onClick(View view) {                        saveMood();                        saveComment();                        dialogBuilder.dismiss();                    }                });                btnCancel.setOnClickListener(new View.OnClickListener() {                    @Override                    public void onClick(View view) {                        dialogBuilder.dismiss();                    }                });                dialogBuilder.setView(dialogView);                dialogBuilder.show();            }        });        //history btn listener, start History Activity        historyButton.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                Intent historyActivityIntent = new Intent(MainActivity.this,                        HistoryActivity.class);                startActivity(historyActivityIntent);            }        });    }    //if a mood has been stored, display,else, set default mood    private void checkMood() {        setContentView(R.layout.activity_main);        ivMood = findViewById(R.id.ivMood);        int mood = mPreferences.getInt(Constants.PREF_KEY_CURRENT_MOOD, 0);        if (mood < 0) {            switch (mood) {                case Constants.BANANA_YELLOW:                    ivMood.setImageResource(R.drawable.great_mood);                    view.setBackgroundResource(R.color.banana_yellow);                    break;                case Constants.LIGHT_SAGE:                    ivMood.setImageResource(R.drawable.good_mood);                    view.setBackgroundResource(R.color.light_sage);                    break;                case Constants.CORNFLOWER_BLUE:                    ivMood.setImageResource(R.drawable.decent_mood);                    view.setBackgroundResource(R.color.cornflower_blue);                    break;                case Constants.WARM_GREY:                    ivMood.setImageResource(R.drawable.bad_mood);                    view.setBackgroundResource(R.color.warm_grey);                    break;                case Constants.FADED_RED:                    ivMood.setImageResource(R.drawable.sad_mood);                    view.setBackgroundResource(R.color.faded_red);                    break;            }        } else {            //set default mood and color if no mood was previously saved            ivMood.setImageResource(R.drawable.great_mood);            view.setBackgroundResource(R.color.banana_yellow);        }    }    //  set alarm intent so that app updates moods at midnight daily    private void setAlarm() {        Calendar calendar = Calendar.getInstance();        calendar.setTimeInMillis(System.currentTimeMillis());//      user HOUR_OF_DAY for 24 hr clock        calendar.add(Calendar.HOUR_OF_DAY, 0);        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);        Intent intent = new Intent(this, MyAlarmReceiver.class);        //pending internet is rapper for intent;        // grants permission to external applications to act on intent        PendingIntent pendingIntent = PendingIntent.getBroadcast(                this, 0, intent, 0        );//      RTC—Fires the pending intent at the specified time but does not wake up the device.        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),                AlarmManager.INTERVAL_DAY, pendingIntent);        System.out.println("alarm set");    }    //  save memo from popup input dialog when user clicks on CONFIRM    public void saveComment() {        mPreferences.edit().putString(                Constants.PREF_KEY_CURRENT_MEMO, editText.getText().toString().trim()).apply();        Toast.makeText(this, "Your memo has been saved!", Toast.LENGTH_SHORT).show();    }    //  save the mood that is currently selected    public void saveMood() {        mPreferences.edit().putInt(Constants.PREF_KEY_CURRENT_MOOD,                ((ColorDrawable) view.getBackground()).getColor()).apply();    }    @Override    public boolean onTouchEvent(MotionEvent event) {        mGestureDetector.onTouchEvent(event);        return super.onTouchEvent(event);    }    @Override    public boolean onTouch(View v, MotionEvent event) {        v.performClick();        return false;    }    //detect if motion event is vertical and significant enough to warrant activity in app    @Override    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent,                           float velocityX, float velocityY) {        //did event get handled here? Assume the negative        boolean result = false;        //gauge movement along y axis for determining if user swiped vertically        //subtract the starting position on the Y axis from the ending position on Y axis        float diffY = moveEvent.getY() - downEvent.getY();        //ignore all non-vertical swipe gestures        //handle vertical swipes        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {            int color = ((ColorDrawable) view.getBackground()).getColor();            if (diffY > 0) {                onSwipeDownwards(color);            } else {                onSwipeUpwards(color);            }            //gesture was handled within this method so result changed to TRUE            result = true;        }        return result;    }    //mood changes from bad to good as user swipes upward    private void onSwipeUpwards(int color) {        switch (color) {            case Constants.FADED_RED:                view.setBackgroundResource(R.color.warm_grey);                ivMood.setImageResource(R.drawable.bad_mood);                break;            case Constants.WARM_GREY:                view.setBackgroundResource(R.color.cornflower_blue);                ivMood.setImageResource(R.drawable.decent_mood);                break;            case Constants.CORNFLOWER_BLUE:                view.setBackgroundResource(R.color.light_sage);                ivMood.setImageResource(R.drawable.good_mood);                break;            case Constants.LIGHT_SAGE:                view.setBackgroundResource(R.color.banana_yellow);                ivMood.setImageResource(R.drawable.great_mood);                break;            case Constants.BANANA_YELLOW:                //do nothing-user must swipe down to go back to lesser mood                break;        }    }    //mood changes from good to bad as user swipes downward    public void onSwipeDownwards(int color) {        switch (color) {            case Constants.BANANA_YELLOW:                view.setBackgroundResource(R.color.light_sage);                ivMood.setImageResource(R.drawable.good_mood);                break;            case Constants.LIGHT_SAGE:                view.setBackgroundResource(R.color.cornflower_blue);                ivMood.setImageResource(R.drawable.decent_mood);                break;            case Constants.CORNFLOWER_BLUE:                view.setBackgroundResource(R.color.warm_grey);                ivMood.setImageResource(R.drawable.bad_mood);                break;            case Constants.WARM_GREY:                view.setBackgroundResource(R.color.faded_red);                ivMood.setImageResource(R.drawable.sad_mood);                break;            case Constants.FADED_RED:                //do nothing -user must swipe up to go back to better mood                break;        }    }    @Override    public boolean onDown(MotionEvent e) {        return false;    }    @Override    public void onShowPress(MotionEvent e) {    }    @Override    public boolean onSingleTapUp(MotionEvent e) {        return false;    }    @Override    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {        return false;    }    @Override    public void onLongPress(MotionEvent e) {    }    @Override    protected void onStart() {        super.onStart();        out.println("MainActivity::onStart()");    }    @Override    protected void onStop() {        super.onStop();        out.println("MainActivity::onStop()");    }    @Override    protected void onDestroy() {        super.onDestroy();        out.println("MainActivity::onDestroy()");    }    @Override    protected void onPause() {        super.onPause();        saveMood();        out.println("MainActivity::onPause()");    }    @Override    protected void onResume() {        super.onResume();        out.println("MainActivity::onResume()");    }}