package com.vendixxx.monitor.common.utils;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuzheng
 * @date 2021-02-25
 * @since 2021
 */
public class ObjectHelperUtils {

    private static final Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 只要有一个不为空
     *
     * @param collection
     * @return
     */
    public static boolean isNotNull(Collection<String> collection) {
        for (String st : collection) {
            if (StringUtils.isEmpty(st)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotNullNum(Collection<Number> collection) {
        for (Number number : collection) {
            if (number == null || number.longValue() < 1) {
                return false;
            }
        }
        return true;
    }

    public static String buildNull(String empty) {
        if (StringUtils.isEmpty(empty)) {
            return null;
        }
        return empty;
    }

    public static List buildEmptyList(List list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

    public static List buildList(List list) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 空字符串
     *
     * @param empty
     * @return
     */
    public static String buildEmpty(String empty) {
        if (StringUtils.isEmpty(empty)) {
            return "";
        }
        return empty;
    }

    public static Long buildLong(long empty) {
        if (empty == 0) {
            return null;
        }
        return empty;
    }

    public static long buildWrapedLong(Long buildLong) {
        if (buildLong == null) {
            return 0L;
        }
        return buildLong.longValue();
    }

    public static Integer buildWrapedInt(Integer empty) {
        if (empty == null) {
            return 0;
        }
        return empty;
    }

    public static Integer buildInt(int empty) {
        if (empty == 0) {
            return null;
        }
        return empty;
    }

    public static BigDecimal buildZeroBig(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return BigDecimal.ZERO;
        }
        return bigDecimal;
    }

    public static BigDecimal buildDecimal(String empty) {
        if (StringUtils.isEmpty(empty)) {
            return null;
        }
        return new BigDecimal(empty);
    }

    public static String buildBig(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "";
        }
        return bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    public static Double buildDouble(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }
        return bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String buildBigPrice(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "";
        }
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    public static String buildBigCompute(BigDecimal before, BigDecimal delta) {
        if (before == null || delta == null) {
            return "";
        }
        return before.add(delta).setScale(3, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    public static Double buildDoubleCompute(BigDecimal before, BigDecimal delta) {
        if (before == null || delta == null) {
            return null;
        }
        return before.add(delta).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 是否大于0
     *
     * @param bigDecimal
     * @return
     */
    public static boolean isOverZero(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return false;
        }
        return bigDecimal.compareTo(BigDecimal.ZERO) == 1;
    }

    /**
     * 是否不为0
     *
     * @param bigDecimal
     * @return
     */
    public static boolean isNotZero(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return false;
        }
        return bigDecimal.compareTo(BigDecimal.ZERO) != 0;
    }

    public static boolean compare(BigDecimal first, BigDecimal second) {
        if (first == null || second == null) {
            return false;
        }
        return first.compareTo(second) == 0;
    }


    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean containsChinese(String str) {
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /*public static void main(String[] args) {

        BigDecimal a = new BigDecimal(0.060).setScale(3, BigDecimal.ROUND_HALF_UP);
        BigDecimal b = new BigDecimal(10.100).setScale(3, BigDecimal.ROUND_HALF_UP);
        BigDecimal c = new BigDecimal(100.050).setScale(3, BigDecimal.ROUND_HALF_UP);
        BigDecimal d = new BigDecimal(100.000).setScale(3, BigDecimal.ROUND_HALF_UP);
        BigDecimal e = new BigDecimal("100.050");
        System.out.println(buildBig(a));
        System.out.println(buildBig(b));
        System.out.println(buildBig(c));
        System.out.println(buildBig(d));
        System.out.println(buildBig(e));
    }*/

    /**
     * 手工分页
     *
     * @param target
     * @param pageSize
     * @param pageNum
     * @param total
     * @param <T>
     * @return
     */
    public static <T> List<T> subList(List<T> target, Integer pageSize, Integer pageNum, Integer total) {
        if (pageNum == null || pageNum < 1) {
            return target;
        }
        // 开始索引
        int fromIndex = (pageNum - 1) * pageSize;
        // 结束索引
        int toIndex = fromIndex + pageSize;
        // 如果结束索引大于集合的最大索引，那么规定结束索引=集合大小
        if (toIndex > total) {
            toIndex = total;
        }
        if (fromIndex <= total) {
            List<T> subList = target.subList(fromIndex, toIndex);
            return subList;
        } else {
            return new ArrayList<>();
        }
    }

}
