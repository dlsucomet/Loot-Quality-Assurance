package tapales.manto.bhuller.loot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewAddPagerAdapter extends FragmentStatePagerAdapter{
    CharSequence Titles[];
    int NumbOfTabs;
    public ViewAddPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb){
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }
    public Fragment getItem(int position){
        if(position == 0){
            AddIncomeFragment aIncomeFragment = new AddIncomeFragment();
            return aIncomeFragment;
        }
        else if(position == 1){
            AddExpenseFragment aExpenseFragment = new AddExpenseFragment();
            return aExpenseFragment;
        }
        return null;
    }
    public CharSequence getPageTitle(int position){
        return Titles[position];
    }
    public int getCount(){
        return NumbOfTabs;
    }
}