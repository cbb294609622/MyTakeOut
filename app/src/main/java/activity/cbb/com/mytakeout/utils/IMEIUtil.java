package activity.cbb.com.mytakeout.utils;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
/**
 *
 * 获取手机唯一标识
 * Created by BoBo on 2015/12/14.
 */
public class IMEIUtil {

    public final static String IMEI_NULL = "000000000000000";
    public static String imieShow(Context mContext) {
        //获取imei
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String imeiOne = tm.getDeviceId();
        if (IMEI_NULL.equals(imeiOne)) {
            //获取拼接的imei
            String imeiTwo = "00"+ Build.BOARD.length()%10+ Build.BRAND.length()%10 + Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 + Build.DISPLAY.length()%10 + Build.HOST.length()%10 + Build.ID.length()%10 + Build.MANUFACTURER.length()%10 + Build.MODEL.length()%10 + Build.PRODUCT.length()%10 + Build.TAGS.length()%10 + Build.TYPE.length()%10 + Build.USER.length()%10 ;

            return imeiTwo;
        }
        return imeiOne;
    }

}

