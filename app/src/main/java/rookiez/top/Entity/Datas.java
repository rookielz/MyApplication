package rookiez.top.Entity;


import java.util.List;

/**
 * Created by rimi on 16/9/24.
 */

public class Datas {
    private String errno;
    private String msg;
    private List<City> cities;

    public String getErrno() {
        return errno;
    }

    public String getMsg() {
        return msg;
    }


    public void setErrno(String errno) {
        this.errno = errno;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
