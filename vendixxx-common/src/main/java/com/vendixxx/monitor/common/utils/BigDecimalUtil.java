package com.vendixxx.monitor.common.utils;

import java.math.BigDecimal;


public class BigDecimalUtil {

    private static final int SCALE = 3;

    /**
     * 字符串转为BigDecimal，四舍五入，保留三位小数
     */
    public static BigDecimal valueOf(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return new BigDecimal(value).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public static boolean eq(BigDecimal v1, BigDecimal v2) {
        if (v1 == null && v2 == null) {
            return true;
        }

        if (v1 == null || v2 == null) {
            return false;
        }

        return v1.compareTo(v2) == 0;
    }


    /**
     * 判断是否大于0的数值
     *
     * @param value
     * @return
     */
    public static boolean isGtZore(BigDecimal value) {
        if (value != null) {
            return value.doubleValue() > 0;
        }
        return false;
    }

    /**
     * 将BigDecimal转为支付串，自动去除小数点后面的0
     *
     * @param value
     * @return
     */
    public static String toString(BigDecimal value) {
        if (value == null) {
            return "";
        }
        String s = String.valueOf(value);
        if (isDecimal(value)) { // 小数
            while (s.endsWith("0")) {
                s = s.substring(0, s.length() - 1);
            }
            return s;
        }

        int i = s.indexOf(".");
        if (i > 0) {
            return s.substring(0, s.indexOf("."));
        }
        return s;
    }

    /**
     * 判断BigDecimal是否存在小数
     *
     * @param number
     * @return
     */
    public static boolean isDecimal(BigDecimal number) {
        if (number == null) {
            return false;
        }
        return new BigDecimal(number.intValue()).compareTo(number) != 0;
    }


    public static BigDecimal mul(BigDecimal b1, BigDecimal b2, int scale) {
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal mul(BigDecimal b1, BigDecimal b2) {
        return mul(b1, b2, SCALE);
    }


    public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
        return add(b1, b2, SCALE);
    }

    public static BigDecimal add(BigDecimal b1, BigDecimal b2, int scale) {
        if (b1 == null) {
            return b2;
        }
        if (b2 == null) {
            return b1;
        }
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


    public static BigDecimal sub(BigDecimal b1, BigDecimal b2, int scale) {
        if (b1 == null) {
            b1 = BigDecimal.ZERO;
        }
        if (b2 == null) {
            b2 = BigDecimal.ZERO;
        }
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal sub(BigDecimal b1, BigDecimal b2) {
        return sub(b1, b2, SCALE);
    }


    public static BigDecimal div(BigDecimal b1, BigDecimal b2, int scale) {
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal div(BigDecimal b1, BigDecimal b2) {
        return div(b1, b2, SCALE);
    }

}