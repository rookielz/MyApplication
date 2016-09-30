package rookiez.top.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by rimi on 16/9/23.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> fragments;
//    private ArrayList<String> titles;

    public MyViewPagerAdapter(FragmentManager fragmentManager,ArrayList<Fragment> fragments){
        super(fragmentManager);
        this.fragments = fragments;
//        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles.get(position);
//    }
}
