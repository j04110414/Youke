package com.tofly.youke.common.utils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 类说明：密码的加密
 * 
 * @author Salman
 */
public class DigestUtil {

    public final static String MD5 = "MD5";
    public final static String SHA_1 = "SHA-1";
    public final static String SHA2_224 = "SHA-224";
    public final static String SHA2_256 = "SHA-256";
    public final static String SHA2_384 = "SHA-384";
    public final static String SHA2_512 = "SHA-512";

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };

    
    public static String uuid() {
    	UUID uuidObj = UUID.randomUUID();
    	return uuidObj.toString();
    }
    
    public static String uuidNoline() {
    	UUID uuidObj = UUID.randomUUID();
    	String uuid = uuidObj.toString();
    	uuid = uuid.replaceAll("-", "");
    	return uuid;
    }
    
    /**
     * encode string
     *
     * @param algorithm
     * @param str
     * @return String
     */
    public static String encode(String str,String algorithm) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes
     *            the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}
