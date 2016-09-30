package rookiez.top.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegOverActivity extends Activity implements View.OnClickListener{

    private Button nowLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_over);
        initView();
    }

    private void initView() {
        nowLogin = (Button) findViewById(R.id.now_login);
        nowLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.now_login:
                startActivity(new Intent(RegOverActivity.this,LoginActivity.class));
                this.finish();
                break;
        }
    }
}
