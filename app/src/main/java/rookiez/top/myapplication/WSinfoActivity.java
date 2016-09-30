package rookiez.top.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WSinfoActivity extends Activity implements View.OnClickListener{

    private Button next,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wsinfo);
        initView();
    }

    private void initView() {
        next = (Button) findViewById(R.id.next);
        back = (Button) findViewById(R.id.wsxx_back);
        next.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next:
                startActivity(new Intent(WSinfoActivity.this,RegOverActivity.class));
                this.finish();
                break;
        }
    }
}
