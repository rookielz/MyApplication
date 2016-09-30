package rookiez.top.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import rookiez.top.myapplication.LoginActivity;
import rookiez.top.myapplication.R;
import rookiez.top.myapplication.SettingActivity;

public class SettingFragment extends Fragment implements View.OnClickListener{


    private String mParam1;
    private String mParam2;

    private Button setting;
    private View view;
    private Button login,reg;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        initView();
        return view;
    }

    private void initView() {
        setting  = (Button) view.findViewById(R.id.settings);
        setting.setOnClickListener(this);
        login = (Button) view.findViewById(R.id.login);
        reg = (Button) view.findViewById(R.id.reg);
        login.setOnClickListener(this);
        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.settings:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.reg:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
