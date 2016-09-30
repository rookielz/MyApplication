package rookiez.top.Entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/25.
 */

public class Districts {
    private String district_id;
    private String district_name;
    private List<BizAreas> biz_areas;

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public List<BizAreas> getBiz_areas() {
        return biz_areas;
    }

    public void setBiz_areas(List<BizAreas> biz_areas) {
        this.biz_areas = biz_areas;
    }
}
