package rookiez.top.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import rookiez.top.Adapter.MyViewPagerAdapter;
import rookiez.top.Fragment.FindFragment;
import rookiez.top.Fragment.IndexFragment;
import rookiez.top.Fragment.InfoFragment;
import rookiez.top.Fragment.SettingFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ViewPager viewPager;

    private ArrayList<Fragment> fragments;


    private ImageButton ib1,ib2,ib3,ib4;
    private TextView t1,t2,t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        initView();
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(),fragments));
        setPage(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        fragments = new ArrayList<Fragment>();
        fragments.add(IndexFragment.newInstance("index","a"));
        fragments.add(FindFragment.newInstance("find","b"));
        fragments.add(InfoFragment.newInstance("info","c"));
        fragments.add(SettingFragment.newInstance());

        ib1 = (ImageButton) findViewById(R.id.ib_index);
        ib2 = (ImageButton) findViewById(R.id.ib_find);
        ib3 = (ImageButton) findViewById(R.id.ib_info);
        ib4 = (ImageButton) findViewById(R.id.ib_setting);

        ib1.setOnClickListener(this);
        ib2.setOnClickListener(this);
        ib3.setOnClickListener(this);
        ib4.setOnClickListener(this);

        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);

        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);


    }

    public void setPage(int i){
        viewPager.setCurrentItem(i);
        ib1.setImageDrawable(getResources().getDrawable(R.mipmap.tab_ic_home_normal));
        ib2.setImageDrawable(getResources().getDrawable(R.mipmap.tab_ic_nearby_normal));
        ib3.setImageDrawable(getResources().getDrawable(R.mipmap.tab_ic_featured_normal));
        ib4.setImageDrawable(getResources().getDrawable(R.mipmap.tab_ic_mine_normal));

        t1.setTextColor(getResources().getColor(R.color.defaultTextColor));
        t2.setTextColor(getResources().getColor(R.color.defaultTextColor));
        t3.setTextColor(getResources().getColor(R.color.defaultTextColor));
        t4.setTextColor(getResources().getColor(R.color.defaultTextColor));


        switch (i){
            case 0:
                ib1.setImageDrawable(getResources().getDrawable(R.mipmap.tab_ic_home_highlight));
                t1.setTextColor(getResources().getColor(R.color.indexTextColors));
                break;
            case 1:
                ib2.setImageDrawable(getResources().getDrawable(R.mipmap.tab_ic_nearby_highlight));
                t2.setTextColor(getResources().getColor(R.color.indexTextColors));
                break;
            case 2:
                ib3.setImageDrawable(getResources().getDrawable(R.mipmap.tab_ic_featured_highlight));
                t3.setTextColor(getResources().getColor(R.color.indexTextColors));
                break;
            case 3:
                ib4.setImageDrawable(getResources().getDrawable(R.mipmap.tab_ic_mine_highlight));
                t4.setTextColor(getResources().getColor(R.color.indexTextColors));
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_index:
                setPage(0);
                break;
            case R.id.ib_find:
                setPage(1);
                break;
            case R.id.ib_info:
                setPage(2);
                break;
            case R.id.ib_setting:
                setPage(3);
                break;
            case R.id.t1:
                setPage(0);
                break;
            case R.id.t2:
                setPage(1);
                break;
            case R.id.t3:
                setPage(2);
                break;
            case R.id.t4:
                setPage(3);
                break;
        }
    }
}
