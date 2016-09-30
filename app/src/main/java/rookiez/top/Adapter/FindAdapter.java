package rookiez.top.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

import rookiez.top.Entity.Deals;
import rookiez.top.Entity.ShopData;
import rookiez.top.Utility.ImageLoading;
import rookiez.top.myapplication.R;

import static android.content.ContentValues.TAG;


/**
 * Created by Administrator on 2016/9/25.
 */

public class FindAdapter extends BaseAdapter {

    private List<ShopData> dealsList;
    private Context context;

    public FindAdapter(Context context,List<ShopData> dealsList){
        this.context = context;
        this.dealsList = dealsList;
    }

    @Override
    public int getCount() {
        return dealsList.size();
    }

    @Override
    public Object getItem(int position) {
        return dealsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.find_list_item,null);
        }
        final ShopData sp = dealsList.get(position);
        Deals deals = sp.getDeals().get(0);
        final ImageView img = (ImageView) convertView.findViewById(R.id.sp_img);
        TextView sp_name = (TextView) convertView.findViewById(R.id.sp_name);
        TextView sp_address = (TextView) convertView.findViewById(R.id.sp_address);
        TextView sp_description = (TextView) convertView.findViewById(R.id.description);
        TextView sp_price = (TextView) convertView.findViewById(R.id.sp_price);
        TextView sp_now_price = (TextView) convertView.findViewById(R.id.sp_now_price);
        TextView sp_sale_num = (TextView) convertView.findViewById(R.id.sale_num);
        TextView sp_promotion_price = (TextView) convertView.findViewById(R.id.promotion_price);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
        sp_price.setText(Integer.valueOf(deals.getMarket_price())/100+"");
        sp_promotion_price.setText(Integer.valueOf(deals.getPromotion_price())/100+"/人");
        sp_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        sp_now_price.setText(Integer.valueOf(deals.getCurrent_price())/100+"");
        sp_sale_num.setText("售出"+deals.getSale_num());
        if (deals!=null)
            ratingBar.setRating(Float.valueOf(deals.getScore().toString()));
        ImageLoading imgloader = new ImageLoading(context);
        imgloader.showImage(sp.getTiny_image(),img);
        sp_name.setText(sp.getShop_name());
        sp_description.setText(deals.getDescription());
        double x = Double.valueOf(sp.getDistance());
        DecimalFormat    df   = new DecimalFormat("######0.0");
        double jl = Double.parseDouble(df.format((x/1000)));
        sp_address.setText("距离"+jl+"km");
        return convertView;
    }
}
