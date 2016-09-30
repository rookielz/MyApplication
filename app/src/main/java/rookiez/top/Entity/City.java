package rookiez.top.Entity;

/**
 * Created by rimi on 16/9/24.
 */

public class City {
    private String city_id;
    private String city_name;
    private String short_name;
    private String map_id;
    private String city_pinyin;
    private String short_pinyin;

    public String getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public String getMap_id() {
        return map_id;
    }

    public String getCity_pinyin() {
        return city_pinyin;
    }

    public String getShort_pinyin() {
        return short_pinyin;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    public void setCity_pinyin(String city_pinyin) {
        this.city_pinyin = city_pinyin;
    }

    public void setShort_pinyin(String short_pinyin) {
        this.short_pinyin = short_pinyin;
    }
}
