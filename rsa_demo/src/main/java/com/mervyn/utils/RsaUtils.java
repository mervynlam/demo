package com.mervyn.utils;

import javafx.scene.control.Alert;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaUtils {
    //加密算法RSA
    private static final String ALGORITHM = "RSA";
    //密钥长度
    private static final int KEY_SIZE = 2048;
    //公钥文件
    private static final String PUBLIC_KEY_FILE = "PublicKey";
    //密钥文件
    private static final String PRIVATE_KEY_FILE = "PrivateKey";
    //最大加密长度
    private static final int MAX_ENCRYPT_BLOCK = KEY_SIZE / 8 -11;
    //最大解密长度
    private static final int MAX_DECRYPT_BLOCK = KEY_SIZE / 8;

    /**
     * @Title: generateKeyPair
     * @Description: TODO(生成并保存密钥对)
     * @author lsy
     * @date  2020年05月21日 10:59:07
     * @param
     * @return void
     * @throws
    */
    public static void generateKeyPair() {
        //获取一个RSA算法的密钥生成实例
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //初始化密钥长度
        keyPairGenerator.initialize(KEY_SIZE);

        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        //编码处理，方便保存
        String publicKeyStr = new BASE64Encoder().encode(publicKey.getEncoded());
        String privateKeyStr = new BASE64Encoder().encode(privateKey.getEncoded());

        //保存输出流
        BufferedWriter pubBw = null;
        BufferedWriter priBw = null;
        try {
            pubBw = new BufferedWriter(new FileWriter(PUBLIC_KEY_FILE));
            priBw = new BufferedWriter(new FileWriter(PRIVATE_KEY_FILE));
            pubBw.write(publicKeyStr);
            priBw.write(privateKeyStr);
            pubBw.flush();
            priBw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pubBw != null) {
                try {
                    pubBw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (priBw != null) {
                try {
                    priBw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Title: getPublicKey
     * @Description: TODO(获取公钥)
     * @author lsy
     * @date  2020年05月21日 11:27:49
     * @param
     * @return java.security.interfaces.RSAPublicKey
     * @throws
    */
    private static RSAPublicKey getPublicKey() {
        //输入流
        BufferedReader pubBr = null;
        RSAPublicKey publicKey = null;
        try {
            pubBr = new BufferedReader(new FileReader(PUBLIC_KEY_FILE));
            String line = null;
            StringBuilder publicKeyEncode = new StringBuilder();
            while ((line = pubBr.readLine()) != null) {
                publicKeyEncode.append(line);
            }

            //base64转为byte
            byte[] publicKeyByte = new BASE64Decoder().decodeBuffer(publicKeyEncode.toString());
            //获取指定算法的密钥工厂, 根据 已编码的公钥规格, 生成公钥对象
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKeyByte);
            publicKey = (RSAPublicKey) KeyFactory.getInstance(ALGORITHM).generatePublic(encodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pubBr != null) {
                try {
                    pubBr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return publicKey;
    }

    /**
     * @Title: getPrivateKey
     * @Description: TODO(获取私钥)
     * @author lsy
     * @date  2020年05月21日 13:56:34
     * @param
     * @return java.security.interfaces.RSAPrivateKey
     * @throws
    */
    private static RSAPrivateKey getPrivateKey() {
        //输入流
        BufferedReader priBr = null;
        RSAPrivateKey privateKey = null;
        try {
            priBr = new BufferedReader(new FileReader(PRIVATE_KEY_FILE));
            String line = null;
            StringBuilder privateKeyEncode = new StringBuilder();
            while ((line = priBr.readLine()) != null) {
                privateKeyEncode.append(line);
            }

            //base64转为byte
            byte[] privateKeyByte = new BASE64Decoder().decodeBuffer(privateKeyEncode.toString());
            //获取指定算法的密钥工厂, 根据 已编码的私钥规格, 生成私钥对象
            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
            privateKey = (RSAPrivateKey) KeyFactory.getInstance(ALGORITHM).generatePrivate(encodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (priBr != null) {
                try {
                    priBr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return privateKey;
    }

    /**
     * @Title: encrypt
     * @Description: TODO(公钥加密)
     * @author lsy
     * @date  2020年05月21日 14:00:56
     * @param sourceStr
     * @return java.lang.String
     * @throws
    */
    public static String encrypt(String sourceStr) {
        //获取公钥
        RSAPublicKey publicKey = getPublicKey();
        String encryptStr = null;
        try {
            //RSA加密
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptStr = new BASE64Encoder().encode(cipher.doFinal(sourceStr.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * @Title: decrypt
     * @Description: TODO(私钥解密)
     * @author lsy
     * @date  2020年05月21日 14:05:53
     * @param encryptStr
     * @return java.lang.String
     * @throws
    */
    public static String decrypt(String encryptStr) {
        //获取私钥
        RSAPrivateKey privateKey = getPrivateKey();
        String sourceStr = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            sourceStr = new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(encryptStr)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceStr;
    }

    /**
     * @Title: encryptSegment
     * @Description: TODO(分段加密)
     * @author lsy
     * @date  2020年05月21日 16:07:04
     * @param sourceStr
     * @return java.lang.String
     * @throws
    */
    public static String encryptSegment(String sourceStr) {
        //获取公钥
        RSAPublicKey publicKey = getPublicKey();
        String encryptStr = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            //获取源文字节
            byte[] sourceByte = sourceStr.getBytes();
            //源文长度
            int sourceLen = sourceByte.length;
            //偏离度
            int offset = 0;
            //分段次数
            int tmp = 0;
            //分段缓存
            byte[] cache;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //循环字节
            while (sourceLen - offset > 0) {
                if (sourceLen - offset > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(sourceByte, offset, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(sourceByte, offset, sourceLen - offset);
                }
                out.write(cache, 0, cache.length);
                tmp++;
                offset = tmp * MAX_ENCRYPT_BLOCK;
            }
            byte[] result = out.toByteArray();
            out.close();
            encryptStr = new BASE64Encoder().encode(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * @Title: decryptSegment
     * @Description: TODO(分段解密)
     * @author lsy
     * @date  2020年05月21日 16:34:34
     * @param encryptStr
     * @return java.lang.String
     * @throws
    */
    public static String decryptSegment (String encryptStr) {
        //获取私钥
        RSAPrivateKey privateKey = getPrivateKey();
        String sourceStr = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //获取加密文字节
            byte[] encryptByte = new BASE64Decoder().decodeBuffer(encryptStr);
            //加密文长度
            int encryptLen = encryptByte.length;
            //偏离度
            int offset = 0;
            //分段次数
            int tmp = 0;
            //分段缓存
            byte[] cache;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //循环字节
            while (encryptLen - offset > 0) {
                if (encryptLen - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptByte, offset, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptByte, offset, encryptLen - offset);
                }
                out.write(cache, 0, cache.length);
                tmp++;
                offset = tmp * MAX_DECRYPT_BLOCK;
            }
            byte[] result = out.toByteArray();
            out.close();
            sourceStr = new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceStr;
    }
}
