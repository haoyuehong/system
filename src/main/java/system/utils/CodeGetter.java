package system.utils;

import java.util.Calendar;
import java.util.Random;

/**
 * @Author: mol
 * @Description:生成设备码
 * @Date: create in 13:56 2018/3/23
 */
public class CodeGetter {

    public static String codeGet() {
        Random random = new Random();
        String ran =  random.nextInt(100)+"";
        Calendar date = Calendar.getInstance();
        String year = date.get(Calendar.YEAR)+"";
        String month = (date.get(Calendar.MONTH)+1)+"";
        String day = date.get(Calendar.DATE)+"";
        String mi = date.get(Calendar.MILLISECOND)+"";
        System.out.println(mi);
        return year+month+day+mi+ran;
    }
}
