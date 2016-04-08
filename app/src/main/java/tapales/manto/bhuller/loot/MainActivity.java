package tapales.manto.bhuller.loot;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private SlidingTabLayout tabSlider;
    private CharSequence tabList[] = {"Income", "Overview" ,"Expenses"};
    public static final int TAB_NUMBERS = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(" Loot");
        toolbar.setLogo(R.drawable.loot_icon_small);
        setSupportActionBar(toolbar);
        pagerAdapter =  new ViewPagerAdapter(getSupportFragmentManager(), tabList, TAB_NUMBERS);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);
        tabSlider = (SlidingTabLayout) findViewById(R.id.tabs);
        tabSlider.setDistributeEvenly(true);
        tabSlider.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabSlider.setViewPager(viewPager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(getBaseContext(), AddItemActivity.class));
                return true;
            case R.id.menu_account:
                startActivity(new Intent(getBaseContext(), MyAccountActivity.class));
                return true;
            case R.id.menu_settings:
                startActivity(new Intent(getBaseContext(), SettingsActivity.class));
                return true;
            case R.id.menu_about:
                Toast.makeText(getApplicationContext(),"About Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        Intent mainIntent = new Intent(getBaseContext(), PasscodeActivity.class);
//        startActivity(mainIntent);
//        finish();
//    }
}
