package com.westos.rbac.util;

import java.util.Arrays;

/**
 * @author yihang
 */
public class StringUtil {

    /**
     * 将字符串转换为整数数组，例如：
     * <pre>
     *     String str = "4,5,6";
     *     Integer[] r = split(str);
     * </pre>
     * 结果为：["4", "5", "6"]
     * @param str 字符串
     * @return 整数数组
     */
    public static Integer[] split(String str) {
        if (str == null || str.length() == 0) {
            return new Integer[0];
        }
        String[] strArray = str.split(",");
        Integer[] intArray = new Integer[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.valueOf(strArray[i]);
        }
        return intArray;
    }

    /**
     * 将整数数组拼接为字符串，以逗号分隔，例如：
     * <pre>
     *     Integer[] array = new Integer[]{1,2,3};
     *     String r = join(array);
     * </pre>
     * 结果为："1,2,3"
     *
     * @param intArray 整数数组
     * @return 拼接后的字符串
     */
    public static String join(Integer[] intArray) {
        if (intArray == null || intArray.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(intArray.length * 3);
        for (int i = 0; i < intArray.length; i++) {
            sb.append(intArray[i]);
            if (i != intArray.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }


}
