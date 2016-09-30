package rookiez.top.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import rookiez.top.Entity.City;
import rookiez.top.myapplication.R;

/**
 * Created by rimi on 16/9/24.
 */

public class CityBaseAdapter extends BaseAdapter {

    private List<City> cityList;
    private Context context;

    public CityBaseAdapter(Context context,List<City> cityList){
        this.cityList = cityList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.city_list_item,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.item_city_name);
        City city = cityList.get(position);
        if (city!=null)
            textView.setText(city.getCity_name());
        return convertView;
    }
}
