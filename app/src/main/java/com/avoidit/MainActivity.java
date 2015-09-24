package com.avoidit;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private TimerTask timerTask;
    private ImageView id_plr;
    final Handler handler = new Handler();
    private RelativeLayout game_id;
    private float i;
    private int p,width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initailize();
        touchListener();
        screenSize();
    }

    private void screenSize() {
       /* DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;*/

        Display display= ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
         width = display.getWidth();
        height = display.getHeight();
    }

    private void touchListener() {
        game_id.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                // Whatever
                p = 0;

                // Toast.makeText(getBaseContext(),"Left Swip made ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeRight() {
                // Whatever
                p = 1;

                // Toast.makeText(getBaseContext(),"Right Swip made ",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initailize() {
        id_plr = (ImageView)findViewById(R.id.id_plr);
        game_id = (RelativeLayout)findViewById(R.id.game_id);
        i = 0;
        p = 1;
    }
    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }
    private void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 6000, 1); //


    }
    private void initializeTimerTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        rl_Switch();
                        player_Velocity();

                    }
        });
    }

            };
    }

    private void player_Velocity() {


            id_plr.setTranslationX (i);
            Log.e("SIZE:",String.valueOf(i)+","+String.valueOf(width));
    }
    private void rl_Switch() {
        if(p ==1) {
            if((i<=(width-(id_plr.getWidth())))) {
                i++;
            }
        }
        else if(p == 0)
        {
            if(i>=0) {
                i--;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}





