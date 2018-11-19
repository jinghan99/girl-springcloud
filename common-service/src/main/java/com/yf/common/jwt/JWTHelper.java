package com.yf.common.jwt;

import com.alibaba.druid.util.Utils;
import com.yf.common.constant.BizConstant;
import com.yf.common.utils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * JWT 帮助类
 */
public class JWTHelper {

    //生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。
    // 它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
    private static final String JWT_SECRET = "ljg_LJG-qazedc-123";

    //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


    /**
     * 由字符串生成加密key
     * @return
     */
    private  static SecretKey generalKey(){
        String stringKey = Utils.md5(JWT_SECRET) ;//本地配置文件中加密的密文

        //本地的密码解码[B@152f6e2
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        // 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

        return key;
    }

    /**
     * 生成token
     *
     * @param jwtInfo
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(JWTInfo jwtInfo, int expire) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUsername())
                .claim(BizConstant.JWT_KEY_USER_ID.getValue(), jwtInfo.getUserId())
                .claim(BizConstant.JWT_KEY_NAME.getValue(), jwtInfo.getUsername())
                .claim(BizConstant.JWT_PASSWORD.getValue(), jwtInfo.getPassword())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(signatureAlgorithm, generalKey())
                .compact();
        return compactJws;
    }


    /**
     * 公钥解析token
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token);
        return claimsJws;
    }
    /**
     * 公钥解析token 获取token中的用户信息
     * @param token
     * @return
     * @throws Exception
     */
    public static JWTInfo getInfoFromToken(String token){
        try {
            Jws<Claims> claimsJws = parserToken(token);
            Claims body = claimsJws.getBody();
            return new JWTInfo(body.getSubject(), StringUtil.nullToString(body.get(BizConstant.JWT_KEY_USER_ID.getValue())),
                    StringUtil.nullToString(body.get(BizConstant.JWT_PASSWORD.getValue())));
        } catch (Exception e) {
            return null;
        }

    }

    public static void main(String[] args) throws Exception {
        String token = generateToken(new JWTInfo("jinghan","123","admin"), 123);

        System.out.println("token: "+token);

        System.out.println("解析："+getInfoFromToken(token).toString());
    }
}
