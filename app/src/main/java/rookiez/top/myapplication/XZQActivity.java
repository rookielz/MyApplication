package rookiez.top.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import rookiez.top.HttpUtil.MyOkHttp;

public class XZQActivity extends AppCompatActivity {

    private int cityId = 0;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xzq);
        cityId = Integer.valueOf(getIntent().getStringExtra("cityId"));
    }

    private void getData(){
        String url = "http://apis.baidu.com/baidunuomi/openapi/districts?city_id="+cityId;
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                result = msg.getData().getString("result").toString().trim();
                if (result.length()>7){
                    Gson gson = new Gson();
                }else {
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                }
            }
        };
        MyOkHttp http = new MyOkHttp(0,url,handler);
    }
}
