package tapales.manto.bhuller.loot;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MyAccountActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewAccountPagerAdapter pagerAdapter;
    private SlidingTabLayout tabSlider;
    private CharSequence tabList[] = {"Statistics", "Achievements"};
    public static final int TAB_NUMBERS = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("My Account");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
        pagerAdapter =  new ViewAccountPagerAdapter(getSupportFragmentManager(), tabList, TAB_NUMBERS);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        tabSlider = (SlidingTabLayout) findViewById(R.id.tabs);
        tabSlider.setDistributeEvenly(true);
        tabSlider.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabSlider.setViewPager(viewPager);
    }
}
