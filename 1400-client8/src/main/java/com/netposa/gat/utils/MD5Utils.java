package com.netposa.gat.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * 
 * @author netposa
 *
 */
@Slf4j
@Component
public class MD5Utils {
    /**
     * 对字符串md5加密(小写+字母)
     *
     * @param str 传入要加密的字符串
     * @return MD5加密后的字符串
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 对字符串md5加密(大写+数字)
     *
     * @param s 传入要加密的字符串
     * @return MD5加密后的字符串
     */

    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成request Nonce
     *
     * @return
     */
    public static String randomNonce() {
        String uuid = UUID.randomUUID().toString();
        String nonce = uuid.replaceAll("-","");
        return nonce.substring(0, 12);
    }

    /**
     * 通过nonce、cnonce校验
     *
     * @param nonce
     * @param cnonce
     * @param response
     * @return
     */
    public static boolean checkAuthDH(String nonce, String cnonce, String response) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(nonce);
        stringBuffer.append(cnonce);
        String md5Str = getMD5(stringBuffer.toString());
        if (md5Str.equals(response)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param userName
     * @param realm
     * @param password
     * @return
     */
    public static String authHA1(String userName, String realm, String password) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(userName);
        stringBuffer.append(":");
        stringBuffer.append(realm);
        stringBuffer.append(":");
        stringBuffer.append(password);

        String HA1 = DigestUtils.md5Hex(stringBuffer.toString());
        return HA1;
    }

    /**
     * AUTH 生成HA2
     *
     * @param method
     * @param digestURI
     * @return
     */
    public static String authHA2(String method, String digestURI) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(method);
        stringBuffer.append(":");
        stringBuffer.append(digestURI);
        String HA2 = DigestUtils.md5Hex(stringBuffer.toString());
        return HA2;
    }

    public static String authResponse(String HA1, String nonce, String nonceCount, String clientNonce, String qop, String HA2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(HA1);
        stringBuffer.append(":");
        stringBuffer.append(nonce);
        stringBuffer.append(":");
        stringBuffer.append(nonceCount);
        stringBuffer.append(":");
        stringBuffer.append(clientNonce);
        stringBuffer.append(":");
        stringBuffer.append(qop);
        stringBuffer.append(":");
        stringBuffer.append(HA2);
        String myResponse = DigestUtils.md5Hex(stringBuffer.toString());
        return myResponse;
    }


    /**
     * HTTP OAuth认证
     *
     * @param HA1
     * @param nonce
     * @param nonceCount
     * @param clientNonce
     * @param qop
     * @param HA2
     * @param response
     * @return
     */
    public static boolean checkAuth(String HA1, String nonce, String nonceCount, String clientNonce, String qop, String HA2, String response) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(HA1);
        stringBuffer.append(":");
        stringBuffer.append(nonce);
        stringBuffer.append(":");
        stringBuffer.append(nonceCount);
        stringBuffer.append(":");
        stringBuffer.append(clientNonce);
        stringBuffer.append(":");
        stringBuffer.append(qop);
        stringBuffer.append(":");
        stringBuffer.append(HA2);
        String myResponse = DigestUtils.md5Hex(stringBuffer.toString());
        log.info("register auth myResponse is:"+myResponse);
        if (response.equals(myResponse)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 十进制转换为十六进制，用于request nc
     *
     * @param valueTen
     * @return
     */
    public static String tenToHex(int valueTen) {
        //将其转换为十六进制并输出
        String strHex = Integer.toHexString(valueTen);
        log.info(valueTen + " [十进制]---->[十六进制] " + strHex);
        //将十六进制格式化输出
        String strHex2 = String.format("%08x", valueTen);
        log.info(valueTen + " [十进制]---->[十六进制] " + strHex2);
        return strHex2;
    }

}
