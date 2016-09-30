package rookiez.top.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import static android.content.ContentValues.TAG;

/**
 * Created by rimi on 16/9/26.
 */

public class Location extends Thread implements AMapLocationListener {

    private Context context;
    private Handler handler;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    public AMapLocationListener mAMapLocationListener = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    public Location(Context context,Handler handler){
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        mLocationClient = new AMapLocationClient(context);
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //异步获取定位结果
        mLocationClient.setLocationListener(this);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        int code = -1;
        String result = "";
        if (amapLocation != null) {
            code = amapLocation.getErrorCode();
            if (amapLocation.getErrorCode() == 0) {
                //解析定位结果
                mLocationClient.stopLocation();
                SharedPreferences sp = context.getSharedPreferences("Address", context.MODE_PRIVATE);
                //存入数据
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("cityId", amapLocation.getCityCode());
                editor.putString("adCode", amapLocation.getAdCode());
                String city = amapLocation.getCity().substring(0,amapLocation.getCity().length()-1);
                editor.putString("city",city);
                editor.putString("location",amapLocation.getLongitude()+","+amapLocation.getLatitude());
                editor.putString("address", amapLocation.getProvince()+amapLocation.getCity()+amapLocation.getDistrict()+amapLocation.getStreet());
                editor.commit();
                result = city;
            }else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.i(TAG, "LocationError: "+amapLocation.getErrorCode()+"--------"+amapLocation.getErrorInfo());
                result = amapLocation.getErrorInfo();
                mLocationClient.stopLocation();
            }
        }
        Message msg = handler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("result",result);
        msg.what = code;
        msg.setData(bundle);
        handler.sendMessage(msg);
    }
}
