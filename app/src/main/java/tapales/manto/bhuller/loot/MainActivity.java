package tapales.manto.bhuller.loot;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;

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
        toolbar.setTitle(" Expenses");
        toolbar.setLogo(R.drawable.loot_icon_small);
        setSupportActionBar(toolbar);
        pagerAdapter =  new ViewPagerAdapter(getSupportFragmentManager(), tabList, TAB_NUMBERS);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new AccordionTransformer());
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
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_account:
                startActivity(new Intent(getBaseContext(), MyAccountActivity.class));
                finish();
                return true;
            case R.id.action_add:
                Intent intent = new Intent(this, AddItemActivity.class);
                if(viewPager.getCurrentItem() == 0){
                    intent.putExtra("caller", "income");
                }
                else intent.putExtra("caller", "expense");
                startActivity(intent);
                return true;
            case R.id.menu_settings:
                startActivity(new Intent(getBaseContext(), SettingsActivity.class));
                return true;
            case R.id.menu_about:
                DialogFragment df = new AboutDialogFragment();
                df.show(getFragmentManager(), "");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
