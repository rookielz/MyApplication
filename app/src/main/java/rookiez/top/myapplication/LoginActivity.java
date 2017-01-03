package rookiez.top.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import rookiez.top.Utility.StaticDatas;

import static android.content.ContentValues.TAG;


public class LoginActivity extends Activity implements View.OnClickListener{


    private TextView loginzh,phone,yzm;
    private LinearLayout zh,sj;
    private Button login_back,getSMS,regBtn,loginBtn;
    private int i = 59;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.getData().getString("data")!=null)
                    Toast.makeText(LoginActivity.this,msg.getData().getString("data").toString(),Toast.LENGTH_LONG).show();
                if (msg.what==0x11){
                    if (i>0){
                        getSMS.setText(i+"s");
                        getSMS.setClickable(false);
                    }
                    else{
                        getSMS.setText("获取验证码");
                        getSMS.setClickable(true);
                    }
                }else if (msg.what == 0x12){
                    Toast.makeText(LoginActivity.this,"验证成功!",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
            }
        };
        initView();
        initSMSData();
    }

    private void initSMSData() {
        EventHandler eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        Log.i(TAG, "提交: "+data.toString());
                        Message msg = handler.obtainMessage();
                        msg.what = 0x12;
                        handler.sendMessage(msg);
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        Log.i(TAG, "afterEvent: "+data.toString());
                        if(result == SMSSDK.RESULT_COMPLETE){
                            boolean smart = (Boolean)data;
                            if(smart) {
                                //通过智能验证
                                Message msg = handler.obtainMessage();
                                Bundle bundle = new Bundle();
                                bundle.putString("data","智能验证成功");
                                msg.setData(bundle);
                                handler.sendMessage(msg);
                            } else {
                                //依然走短信验证
                                Message msg = handler.obtainMessage();
                                Bundle bundle = new Bundle();
                                bundle.putString("data","验证码已发送");
                                msg.setData(bundle);
                                handler.sendMessage(msg);
                            }
                        }
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
//                        Log.i(TAG, "afterEvent: "+data.toString());
                    }
                }else{
                    Message msg = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putString("data","验证码错误");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    private void initView() {
        SMSSDK.initSDK(this, StaticDatas.SMS_APPKEY,StaticDatas.SMS_APPSecrect);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginzh = (TextView) findViewById(R.id.login_zh);
        zh = (LinearLayout) findViewById(R.id.zh);
        sj = (LinearLayout) findViewById(R.id.sj);
        phone = (TextView) findViewById(R.id.phone);
        yzm = (TextView) findViewById(R.id.yzm);
        login_back = (Button) findViewById(R.id.login_back);
        getSMS = (Button) findViewById(R.id.get_sms_btn);
        regBtn = (Button) findViewById(R.id.reg_btn);

        login_back.setOnClickListener(this);
        getSMS.setOnClickListener(this);
        loginzh.setOnClickListener(this);
        regBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    private void setBtnText(){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        for (; i > -1 ;i--){
                            Message msg = handler.obtainMessage();
                            msg.what = 0x11;
                            handler.sendMessage(msg);
                            sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
    }

    @Override
    public void onClick(View v) {
        String phoneNumber = phone.getText().toString().trim();
        String code = yzm.getText().toString().trim();
        switch (v.getId()){
            case R.id.login_zh:
                if (zh.getVisibility()==View.VISIBLE){
                    zh.setVisibility(View.GONE);
                    sj.setVisibility(View.VISIBLE);
                    loginzh.setText("账号密码登录");
                }else if (sj.getVisibility()==View.VISIBLE){
                    sj.setVisibility(View.GONE);
                    zh.setVisibility(View.VISIBLE);
                    loginzh.setText("手机短信验证登录");
                }
                break;
            case R.id.login_back:
                this.finish();
                break;
            case R.id.get_sms_btn:
                i = 59;
                if (StaticDatas.isMobileNO(phoneNumber)){
                    SMSSDK.getVerificationCode("86",phoneNumber);
                    setBtnText();
                }
                else
                    Toast.makeText(LoginActivity.this,"手机号格式不正确",Toast.LENGTH_LONG).show();
                break;
            case R.id.reg_btn:
                startActivity(new Intent(LoginActivity.this,RegActivity.class));
                break;
            case R.id.login_btn:
                if (yzm.getText()!=null){
                    if (code.length()!=4)
                        Toast.makeText(LoginActivity.this,"验证码长度错误",Toast.LENGTH_LONG).show();
                    else
                        SMSSDK.submitVerificationCode("86", phoneNumber, code);
                }else
                    Toast.makeText(LoginActivity.this,"验证码不能为空",Toast.LENGTH_LONG).show();
                break;
        }
    }

}
