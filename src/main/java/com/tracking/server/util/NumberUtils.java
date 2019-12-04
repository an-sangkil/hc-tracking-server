package com.tracking.server.util;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|Mezzomedia. All right reserved.
 * @since 2019-12-03
 */
public class NumberUtils {

    /**
     * 최소값, 최대값 범위 안에서 난수 발생
     *
     * @param min 최소값
     * @param max 최대값
     * @return
     */
    public double getRandomDoubleBetweenRange(double min, double max) {
        double x = (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

}
