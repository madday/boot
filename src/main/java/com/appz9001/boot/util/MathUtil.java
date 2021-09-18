package com.appz9001.boot.util;

import java.math.BigDecimal;

public class MathUtil {

    private static BigDecimal ZERO = new BigDecimal("0");

    /**
     * 数值是否是0
     * @param num
     * @return
     */
    public static boolean isZero(BigDecimal num){
        if(num == null||num.compareTo(ZERO) == 0 ){
            return true;
        }
        return false;
    }
}
