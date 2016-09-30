package rookiez.top.HttpUtil;

import android.content.Entity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rimi on 16/9/24.
 */

public class MyOkHttp extends Thread{

    private String uri = null;
    private Handler handler = null;
    private int n = 0;

    public MyOkHttp(int n,String url,Handler handler){
        this.handler = handler;
        this.uri = url;
        this.n = n;
    }

    @Override
    public void run() {
        super.run();
        String result = "";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(uri).addHeader("apikey","b5ce3295f74fdfb116d46937af022eb5").build();
//        String result = null;
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful())
                result = response.body().string();
            else
                result = "网络请求失败";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
           result = URLDecoder.decode(result, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("result",result);
        msg.setData(bundle);
        msg.what = n;
        handler.sendMessage(msg);
    }
}
