package rookiez.top.Entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/25.
 */

public class ShopData {
    private String shop_id;
    private String shop_name;
    private String longitude;
    private String latitude;
    private String distance;
    private String deal_num;
    private String shop_url;
    private String shop_murl;
    private String per_price;
    private String average_score;
    private String address;
    private String tiny_image;
    private List<Deals> deals;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDeal_num() {
        return deal_num;
    }

    public void setDeal_num(String deal_num) {
        this.deal_num = deal_num;
    }

    public String getShop_url() {
        return shop_url;
    }

    public void setShop_url(String shop_url) {
        this.shop_url = shop_url;
    }

    public String getShop_murl() {
        return shop_murl;
    }

    public void setShop_murl(String shop_murl) {
        this.shop_murl = shop_murl;
    }

    public String getPer_price() {
        return per_price;
    }

    public void setPer_price(String per_price) {
        this.per_price = per_price;
    }

    public String getAverage_score() {
        return average_score;
    }

    public void setAverage_score(String average_score) {
        this.average_score = average_score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTiny_image() {
        return tiny_image;
    }

    public void setTiny_image(String tiny_image) {
        this.tiny_image = tiny_image;
    }

    public List<Deals> getDeals() {
        return deals;
    }

    public void setDeals(List<Deals> deals) {
        this.deals = deals;
    }
}
