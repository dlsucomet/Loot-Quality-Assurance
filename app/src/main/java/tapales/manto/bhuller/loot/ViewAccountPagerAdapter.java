package tapales.manto.bhuller.loot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewAccountPagerAdapter extends FragmentStatePagerAdapter{
    CharSequence Titles[];
    int NumbOfTabs;
    public ViewAccountPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb){
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }
    public Fragment getItem(int position){
        if(position == 0){
            MyAccount mAccount = new MyAccount();
            return mAccount;
        }
        else if(position == 1){
            AchievementList aList = new AchievementList();
            return aList;
        }
        else return null;
    }
    public CharSequence getPageTitle(int position){
        return Titles[position];
    }
    public int getCount(){
        return NumbOfTabs;
    }
}