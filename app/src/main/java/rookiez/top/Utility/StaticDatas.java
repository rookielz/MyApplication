package rookiez.top.Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rimi on 16/9/29.
 */

public class StaticDatas {
    public static String SMS_APPKEY = "1753584e56740";
    public static String SMS_APPSecrect = "4d1cdca6d6f3d0f4e8de3ab21e49b07b";

    public static boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(17[0,7])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

        }
}
