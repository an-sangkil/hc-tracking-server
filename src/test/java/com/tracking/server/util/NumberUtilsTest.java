package com.tracking.server.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

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
@Slf4j
public class NumberUtilsTest {

    NumberUtils numberUtils = new NumberUtils();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void randomCount() {
        IntStream.rangeClosed(1, 50).forEach(value -> {
            // 41~50 random
            var data = new Random().nextInt(10) + 41;
            log.info("random ={}", data);
        });
    }

    @Test
    public void getRandomDoubleBetweenRange() {
        IntStream.rangeClosed(1, 10).forEach(value -> {
            var randomDouble = numberUtils.getRandomDoubleBetweenRange(5.0, 100.00);
            log.info("random double = {}", randomDouble);
        });

    }
}