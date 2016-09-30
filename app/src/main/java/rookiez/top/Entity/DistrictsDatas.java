package rookiez.top.Entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/25.
 */

public class DistrictsDatas {
    private String errno;
    private String msg;
    private List<Districts> districts;

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Districts> getDistricts() {
        return districts;
    }

    public void setDistricts(List<Districts> districts) {
        this.districts = districts;
    }
}
