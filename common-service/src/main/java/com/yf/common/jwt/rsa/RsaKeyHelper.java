package com.yf.common.jwt.rsa;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ace on 2017/9/10.
 */
public class RsaKeyHelper {


    private static final String JWT_SECRET = "ljg_LJG-qazedc-123";


    /**
     * 获取公钥
     *
     * @param publicKey
     * @return
     * @throws Exception
     */
    public PublicKey getPublicKey(byte[] publicKey) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    /**
     * 获取密钥
     *
     * @param privateKey
     * @return
     * @throws Exception
     */
    public PrivateKey getPrivateKey(byte[] privateKey) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    /**
     * 生存rsa公钥和密钥
     *
     * @param publicKeyFilename
     * @param privateKeyFilename
     * @param password
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void generateKey(String publicKeyFilename, String privateKeyFilename, String password) throws IOException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        FileOutputStream fos = new FileOutputStream(publicKeyFilename);
        fos.write(publicKeyBytes);
        fos.close();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        fos = new FileOutputStream(privateKeyFilename);
        fos.write(privateKeyBytes);
        fos.close();
    }

    /**
     * 生成rsa公钥
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generatePublicKey() throws IOException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(JWT_SECRET.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        return keyPair.getPublic().getEncoded();
    }

    /**
     * 生成rsa密钥
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generatePrivateKey() throws IOException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(JWT_SECRET.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        return keyPair.getPrivate().getEncoded();
    }

    public static Map<String, byte[]> generateKey() throws IOException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(JWT_SECRET.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        Map<String, byte[]> map = new HashMap<String, byte[]>();
        map.put("pub", publicKeyBytes);
        map.put("pri", privateKeyBytes);
        return map;
    }

    public static String toHexString(byte[] b) {
        return (new BASE64Encoder()).encodeBuffer(b);
    }

    public static final byte[] toBytes(String s) throws IOException {
        return (new BASE64Decoder()).decodeBuffer(s);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        SecureRandom secureRandom = new SecureRandom("123".getBytes());
//        keyPairGenerator.initialize(1024, secureRandom);
//        KeyPair keyPair = keyPairGenerator.genKeyPair();
//        System.out.println(keyPair.getPublic().getEncoded());

        byte[] pubkey = generatePublicKey();



    }

}

