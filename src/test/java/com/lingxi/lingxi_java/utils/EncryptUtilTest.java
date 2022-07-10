package com.lingxi.lingxi_java.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b><code>EncryptUtilTest</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2022/7/5 08:36.
 *
 * @author yasinshaw
 * @since lingxi_java 1.0
 */
class EncryptUtilTest {

    @Test
    void md5() {
        String encryptedText = EncryptUtil.md5("test");
        String encryptedText2 = EncryptUtil.md5("test");
        System.out.println(encryptedText);
        assertEquals(encryptedText, encryptedText2);
    }
}