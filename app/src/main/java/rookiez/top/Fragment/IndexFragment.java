package rookiez.top.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import rookiez.top.Utility.Location;
import rookiez.top.myapplication.CityActivity;
import rookiez.top.myapplication.R;

import static android.content.Context.MODE_PRIVATE;


public class IndexFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Button cityBtn;
    private View view;

    // TODO: Rename and change types and number of parameters
    public static IndexFragment newInstance(String param1, String param2) {
        IndexFragment fragment = new IndexFragment();
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
        view = inflater.inflate(R.layout.fragment_index, container, false);
        cityBtn = (Button) view.findViewById(R.id.city_btn);
        cityBtn.setOnClickListener(this);
        getLocation();
        return view;
    }

    public void getLocation(){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        cityBtn.setText(msg.getData().getString("result").toString().trim());
                        break;
                    default:
                        Toast.makeText(getActivity(),"定位失败,请手动选择位置",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
        Location location = new Location(getActivity(),handler);
        location.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_btn:
                startActivity(new Intent(getActivity(), CityActivity.class));
                break;
        }
    }


}
