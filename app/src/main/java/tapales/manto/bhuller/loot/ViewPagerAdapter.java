package tapales.manto.bhuller.loot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{
    CharSequence Titles[];
    int NumbOfTabs;
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb){
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }
    public Fragment getItem(int position){
        if(position == 0){
            IncomeList iList = new IncomeList();
            return iList;
        }
        else if(position == 1){
            Overview oView = new Overview();
            return oView;
        }
        else if(position == 2){
            ExpenseList eList = new ExpenseList();
            return eList;
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