package tapales.manto.bhuller.loot;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity{
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent mainIntent = new Intent(getBaseContext(), PasscodeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(getBaseContext(), PasscodeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    @Override
    protected void onResume(){
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(getBaseContext(), PasscodeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
