package com.alibaba.datax.core.util;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jingxing on 14/12/15.
 */
public class SecretUtilTest {

    @Test
    public void testRsa() throws Exception {
        Field field = SecretUtil.class.getDeclaredField("KEY_ALGORITHM");
        field.setAccessible(true);
        field.set(SecretUtil.class, "RSA");
        String[] keys = SecretUtil.initKey();
        String publicKey = keys[0];
        String privateKey = keys[1];
        System.out.println("publicKey:" + publicKey);
        System.out.println("privateKey:" + privateKey);
        String data = "阿里巴巴DataX";

        System.out.println("【加密前】：" + data);

        // 加密
        String cipherText = SecretUtil.encrypt(data, publicKey);
        System.out.println("【加密后】：" + cipherText);

        // 解密
        String plainText = SecretUtil.decrypt(cipherText, privateKey);
        System.out.println("【解密后】：" + plainText);

        Assert.assertTrue(plainText.equals(data));
    }

    @Test
    public void testDes() throws Exception {
        Field field = SecretUtil.class.getDeclaredField("KEY_ALGORITHM");
        field.setAccessible(true);
        field.set(SecretUtil.class, "DESede");
        
        String keyContent = "datax&cdp&dsc";
        System.out.println("keyContent:" + keyContent);
        String data = "阿里巴巴DataX";

        System.out.println("【加密前】：" + data);

        // 加密
        String cipherText = SecretUtil.encrypt(data, keyContent);
        System.out.println("【加密后】：" + cipherText);

        // 解密
        String plainText = SecretUtil.decrypt(cipherText, keyContent);
        System.out.println("【解密后】：" + plainText);

        Assert.assertTrue(plainText.equals(data));
    }
    
    @Test
    public void testPythonSwitchJava() {
        String data = "rootroot";
        String key = "abcabcabcabcabcabcabcabc";
        String cipherText = SecretUtil.encrypt3DES(data, key);
        System.out.println(String.format(
                "data[%s] : key[%s] -> cipherText[%s]", data, key, cipherText));
        Assert.assertTrue("svj4x04Oaq6WhrfZVsSRqA==".equals(cipherText));
        Assert.assertTrue(data.equals(SecretUtil.decrypt3DES(cipherText, key)));

        data = "root";
        key = "abcabcabcabcabcabcabcabc";
        cipherText = SecretUtil.encrypt3DES(data, key);
        System.out.println(String.format(
                "data[%s] : key[%s] -> cipherText[%s]", data, key, cipherText));
        Assert.assertTrue("0Y08MKGhNIw=".equals(cipherText));
        Assert.assertTrue(data.equals(SecretUtil.decrypt3DES(cipherText, key)));

        data = "rootroot";
        key = "abc";
        cipherText = SecretUtil.encrypt3DES(data, key);
        System.out.println(String.format(
                "data[%s] : key[%s] -> cipherText[%s]", data, key, cipherText));
        Assert.assertTrue("dUTw4gQQ30qtMDBX0lTpmg==".equals(cipherText));
        Assert.assertTrue(data.equals(SecretUtil.decrypt3DES(cipherText, key)));

        data = "root";
        key = "abc";
        cipherText = SecretUtil.encrypt3DES(data, key);
        System.out.println(String.format(
                "data[%s] : key[%s] -> cipherText[%s]", data, key, cipherText));
        Assert.assertTrue("TRc4s8bf9Yg=".equals(cipherText));
        Assert.assertTrue(data.equals(SecretUtil.decrypt3DES(cipherText, key)));

        data = "rootrootrootroot";
        key = "abc";
        cipherText = SecretUtil.encrypt3DES(data, key);
        System.out.println(String.format(
                "data[%s] : key[%s] -> cipherText[%s]", data, key, cipherText));
        Assert.assertTrue("dUTw4gQQ30p1RPDiBBDfSq0wMFfSVOma".equals(cipherText));
        Assert.assertTrue(data.equals(SecretUtil.decrypt3DES(cipherText, key)));
    }

    @Test
    public void test() throws Exception {
        Field field = SecretUtil.class.getDeclaredField("KEY_ALGORITHM");
        field.setAccessible(true);
        field.set(SecretUtil.class, "RSA");
        this.testRsa();
        System.out.println("\n\n");
        field.set(SecretUtil.class, "DESede");
        this.testDes();

        try {
            field.set(SecretUtil.class, "RDS");
            this.testDes();
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }
}
