package com.liuyang19900520.iotmobile_android.util;



import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by liuyang on 2018/3/17
 */

public class CryptoUtil {

    private static String APP_KEY="E2B7B173A8801CD1455878A1383B2337";

    // HMAC 加密算法名称
    public static final String HMAC_MD5 = "HmacMD5";// 128位
    public static final String HMAC_SHA1 = "HmacSHA1";// 126
    public static final String HMAC_SHA256 = "HmacSHA256";// 256
    public static final String HMAC_SHA512 = "HmacSHA512";// 512

    /**
     * 生成HMAC摘要
     *
     * @param plaintext 明文
     * @return 摘要
     */
    public static String hmacDigest(String plaintext) {
        try {
            Mac mac = Mac.getInstance(HMAC_MD5);
            byte[] secretByte = APP_KEY.getBytes();
            byte[] dataBytes = plaintext.getBytes();
            SecretKey secret = new SecretKeySpec(secretByte, HMAC_MD5);
            mac.init(secret);
            byte[] doFinal = mac.doFinal(dataBytes);
            return byte2HexStr(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 字节数组转字符串
     *
     * @param bytes 字节数组
     * @return 字符串
     */
    private static String byte2HexStr(byte[] bytes) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; bytes != null && n < bytes.length; n++) {
            stmp = Integer.toHexString(bytes[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }


}
