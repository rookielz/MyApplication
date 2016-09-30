package rookiez.top.Fragment;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rookiez.top.Adapter.FindAdapter;
import rookiez.top.Entity.ShopData;
import rookiez.top.HttpUtil.MyOkHttp;
import rookiez.top.myapplication.R;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class FindFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private ListView listView;
    private LinearLayout title,loading;
    private Button type,jl,zhb,sx;
    private PopupWindow popupWindow,p2;
    private TextView address;
    private ScrollView content;
    private AnimationDrawable anim;
    private ImageView load;

    public FindFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FindFragment newInstance(String param1, String param2) {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);
        initView();
        getData();
        makePOP();
        return view;
    }

    private void initView() {
        SharedPreferences sp = getActivity().getSharedPreferences("Address", getActivity().MODE_PRIVATE);
        address = (TextView) view.findViewById(R.id.address);
        address.setText("当前:"+sp.getString("address","未知位置").toString());
        listView = (ListView) view.findViewById(R.id.find_list);
        title = (LinearLayout) view.findViewById(R.id.find_title);
        type = (Button) view.findViewById(R.id.type);
        zhb = (Button) view.findViewById(R.id.zhb);
        jl = (Button) view.findViewById(R.id.juli);
        sx = (Button) view.findViewById(R.id.sx);
        content = (ScrollView) view.findViewById(R.id.find_content);
        loading = (LinearLayout) view.findViewById(R.id.loading_max);
        load = (ImageView) view.findViewById(R.id.lo);
        anim = (AnimationDrawable) load.getDrawable();
        anim.start();
        type.setOnClickListener(this);
        jl.setOnClickListener(this);
        sx.setOnClickListener(this);
        zhb.setOnClickListener(this);
    }

    private void getData(){
        SharedPreferences sp = getActivity().getSharedPreferences("Address", MODE_PRIVATE);
        Log.i(TAG, "getData: "+sp.getString("cityId","0"));
        Log.i(TAG, "getData: "+sp.getString("location","0,0"));
        String url = "http://apis.baidu.com/baidunuomi/openapi/searchshops?city_id=800010000&location="+sp.getString("location","0,0");
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int count = 0;
                JSONArray arr = null;
                String result = msg.getData().getString("result").toString().trim();
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getInt("errno")==0){
                        arr = obj.getJSONObject("data").getJSONArray("shops");
                        count = arr.length();
                        result = arr.toString();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                List<ShopData> lis = new ArrayList<ShopData>();
                for (int i = 0;i<count;i++){
                    ShopData sd = null;
                    try {
                        sd = (ShopData) gson.fromJson(arr.getJSONObject(i).toString(),ShopData.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    lis.add(sd);
                }
                anim.stop();
                FindAdapter adapter = new FindAdapter(getActivity(),lis);
                loading.setVisibility(View.GONE);
                title.setVisibility(View.VISIBLE);
                address.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);
                listView.setAdapter(adapter);
            }
        };
        MyOkHttp http = new MyOkHttp(0,url,handler);
        http.start();
    }
    public void makePOP(){
        p2 = new PopupWindow();
        LinearLayout lin = new LinearLayout(getActivity());
        p2.setContentView(lin);
        p2.setOutsideTouchable(true);
        p2.setBackgroundDrawable(new ColorDrawable(0x55000000));
        p2.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        p2.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow = new PopupWindow();
        LayoutInflater inflater1 = LayoutInflater.from(getActivity());
        View v1 = inflater1.inflate(R.layout.pop,null);
        Resources resources = getActivity().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        popupWindow.setContentView(v1);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xFFF1F2F3));
        popupWindow.setHeight(dm.heightPixels/2);
        popupWindow.setWidth(dm.widthPixels);
        popupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
        popupWindow.setOnDismissListener(new poponDismissListener());

    }
    public void showPop(int i){
        if (!(popupWindow.isShowing()&&p2.isShowing())){
            backgroundAlpha(0.5f);
            popupWindow.showAsDropDown(title);
//            p2.showAsDropDown(title);
        }
    }
    @Override
    public void onClick(View v) {
        type.setTextColor(getResources().getColor(R.color.defaultTextColor));
        zhb.setTextColor(getResources().getColor(R.color.defaultTextColor));
        jl.setTextColor(getResources().getColor(R.color.defaultTextColor));
        sx.setTextColor(getResources().getColor(R.color.defaultTextColor));

        switch (v.getId()){
            case R.id.type:
                showPop(1);
                type.setTextColor(getResources().getColor(R.color.indexTextColors));
                break;
            case R.id.juli:
                showPop(2);
                jl.setTextColor(getResources().getColor(R.color.indexTextColors));
                break;
            case R.id.zhb:
                showPop(3);
                zhb.setTextColor(getResources().getColor(R.color.indexTextColors));
                break;
            case R.id.sx:
                showPop(4);
                sx.setTextColor(getResources().getColor(R.color.indexTextColors));
                break;
        }
    }

    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }
}
