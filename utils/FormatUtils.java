
/**
 * 主要对一些常用的字符串转数字操作进行了封装，避免出现运行时crash
 * 把异常抓了，返回0
 */
public class FormatUtils {
    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            Log.d("NumFormat", value + "不能被解析成int");
            return 0;
        }
    }

    public static float parseFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            Log.d("NumFormat", value + "不能被解析成float");
            return 0;
        }
    }

    public static long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            Log.d("NumFormat", value + "不能被解析成long");
            return 0;
        }
    }

    public static double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            Log.d("NumFormat", value + "不能被解析成double");
            return 0;
        }
    }

    /**
     * 把价格每隔三位中间用"，"隔开
     *
     * @param price
     * @return
     */
    public static String getSplitPrice(double price) {
        NumberFormat nf = new DecimalFormat("##,###,###,###,###,###,###.########");
        return nf.format(price);
    }

    /**
     * 把电话号码银行卡号每隔四位中间用" "隔开
     *
     * @param num要切割的字符串
     * @param start是否从前面切割，false后面，true前面
     * @return
     */
    public static String getSplitPhone(String num, boolean start) {
        char[] charArray = num.toCharArray();
        int left = start ? 0 : (charArray.length % 4);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (i != 0 && i % 4 == left) {
                builder.append(" ");
            }
            builder.append(charArray[i]);
        }
        return builder.toString();
    }

    /**
     * 把价格每隔三位中间用"，"隔开
     *
     * @param price
     * @return
     */
    public static double parseSplitPrice(String price) {
        NumberFormat nf = new DecimalFormat("##,###,###,###,###,###,###.########");
        try {
            return nf.parse(price).doubleValue();
        } catch (ParseException e) {
            Log.d("NumFormat", price + "不能被格式化为double");
            return 0;
        }
    }


    /**
     * @param date 单位：秒
     * @return
     */
    public static String getAfterDate(long date) {
        int day = (int) (date / (3600 * 24));
        int hour = (int) (date % (3600 * 24)) / 3600;
        int min = (int) ((date % (3600 * 24)) % 3600) / 60;
        return (day == 0 ? "" : (day + "天")) + hour + "时" + min + "分";
    }

    /**
     * 过去的一个时间段格式化为今天，昨天,时间
     *
     * @param date
     * @return
     */
    public static String getTime(long times) {
        String todySDF = "今天 HH:mm";
        String yesterDaySDF = "昨天 HH:mm";
        String otherSDF = "yyyy年M月d日 HH:mm";
        SimpleDateFormat sfd = null;
        String time = "";
        Calendar dateCalendar = Calendar.getInstance();
        Date date = new Date(times);
        dateCalendar.setTime(date);
        Date now = new Date();
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(now);
        targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
        targetCalendar.set(Calendar.MINUTE, 0);
        if (dateCalendar.after(targetCalendar)) {
            sfd = new SimpleDateFormat(todySDF);
            time = sfd.format(date);
            return time;
        } else {
            targetCalendar.add(Calendar.DATE, -1);
            if (dateCalendar.after(targetCalendar)) {
                sfd = new SimpleDateFormat(yesterDaySDF);
                time = sfd.format(date);
                return time;
            }
        }
        sfd = new SimpleDateFormat(otherSDF);
        time = sfd.format(date);
        return time;
    }
}
