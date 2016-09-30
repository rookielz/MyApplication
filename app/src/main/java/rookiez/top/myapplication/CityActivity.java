package rookiez.top.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import rookiez.top.Adapter.CityBaseAdapter;
import rookiez.top.Entity.City;
import rookiez.top.Entity.Datas;
import rookiez.top.HttpUtil.MyOkHttp;
import rookiez.top.Utility.Location;

public class CityActivity extends Activity implements OnClickListener,AdapterView.OnItemClickListener{

    private Handler handler = null;

    private ScrollView cityScrol;
    private EditText search;
    private ListView listView;
    private CityBaseAdapter adapter;
    private List<City> cityList;
    private TextView location;
    private Button back;
    private LinearLayout CityRoot,cityLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initView();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int i = msg.what;
                Message m = msg;
                switch (i){
                    case 200:
                        String result = m.getData().getString("result").toString().trim();
                        if (result.length()<7)
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                        else{
                            Log.i("Result",result);
                            Gson gson = new Gson();
                            Datas datas= gson.fromJson(result,Datas.class);
                            cityList = new ArrayList<City>();
                            cityList = datas.getCities();
                            adapter = new CityBaseAdapter(getApplicationContext(),cityList);
                            listView.setAdapter(adapter);
                            setListViewHeight(listView);
                            cityLoad.setVisibility(View.GONE);
                            CityRoot.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 0:
                        back.setText("当前城市-"+m.getData().getString("result").toString().trim());
                        location.setText(m.getData().getString("result").toString().trim()+"市");
                        break;
                    default:
                        location.setText("定位失败,点击重试");
                        Toast.makeText(getApplicationContext(),"请检查你的网络!",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
        getLocation();
        getCityData();
    }

    public void initView(){
        CityRoot = (LinearLayout) findViewById(R.id.activity_city_root);
        listView = (ListView) findViewById(R.id.cityList);
        search = (EditText) findViewById(R.id.city_search_edit);
        cityScrol = (ScrollView) findViewById(R.id.city_scrollView);
        location = (TextView) findViewById(R.id.location_get);
        location.setOnClickListener(this);
        back = (Button) findViewById(R.id.city_back);
        back.setOnClickListener(this);
        CityRoot.setVisibility(View.GONE);
        cityLoad = (LinearLayout) findViewById(R.id.city_loading);
        listView.setOnItemClickListener(this);
        location.setText("正在获取定位...");
    }

    public void getLocation() {
        location.setText("正在获取定位...");
        Location loca = new Location(getApplicationContext(),handler);
        loca.start();
    }

    public void getCityData(){
        String url = "http://apis.baidu.com/baidunuomi/openapi/cities";
        MyOkHttp http = new MyOkHttp(200,url,handler);
        http.start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.location_get:
                getLocation();
                break;
            case R.id.city_back:
                finish();
                break;
        }
    }
    public static void setListViewHeight(ListView listView) {
        try {
            int totalHeight = 0;
            ListAdapter adapter = listView.getAdapter();
            for (int i = 0, len = adapter.getCount(); i < len; i++) { // listAdapter.getCount()
                View listItem = adapter.getView(i, null, listView);
                listItem.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight
                    + (listView.getDividerHeight() * (listView.getCount() - 1));
            listView.setLayoutParams(params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(CityActivity.this,XZQActivity.class).putExtra("cityId",cityList.get(position).getCity_id()));
    }
}
