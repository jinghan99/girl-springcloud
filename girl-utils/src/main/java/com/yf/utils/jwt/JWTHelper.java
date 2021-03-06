package com.yf.utils.jwt;

import com.alibaba.druid.util.Utils;
import com.yf.utils.common.StringUtil;
import com.yf.utils.constant.BizConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * JWT 帮助类
 */
public class JWTHelper {

    /**
     * 时间颗粒度 秒
     */
    private static final int EXPIRE = 30*60;


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
    public static String generateToken(JWTInfo jwtInfo, int expire){
        if(expire == 0){
            expire = EXPIRE;
        }
        String token = Jwts.builder()
                .setSubject(jwtInfo.getUsername())
                .claim(BizConstant.JWT_KEY_USER_ID.getValue(), jwtInfo.getUserId())
                .claim(BizConstant.JWT_KEY_NAME.getValue(), jwtInfo.getUsername())
                .claim(BizConstant.JWT_PASSWORD.getValue(), jwtInfo.getPassword())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(signatureAlgorithm, generalKey())
                .compact();
        return token;
    }


    /**
     * 公钥解析token
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims parserToken(String token){
        return Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token).getBody();
    }

    /**
     * 公钥解析token 获取token中的用户信息
     * @param token
     * @return
     * @throws Exception
     */
    public static JWTInfo getInfoFromToken(String token){
        try {
            Claims body = parserToken(token);
            return new JWTInfo(body.getSubject(), StringUtil.nullToString(body.get(BizConstant.JWT_KEY_USER_ID.getValue())),
                    StringUtil.nullToString(body.get(BizConstant.JWT_PASSWORD.getValue())));
        } catch (Exception e) {
            return null;
        }

    }

    public static String getUsernameFromToken(String token){
        try {
            Claims body = parserToken(token);
            return new JWTInfo(body.getSubject(), StringUtil.nullToString(body.get(BizConstant.JWT_KEY_USER_ID.getValue())),
                    StringUtil.nullToString(body.get(BizConstant.JWT_PASSWORD.getValue()))).getUsername();
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = parserToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public static Boolean validateToken(String token, UserDetails userDetails) {
        JWTInfo user = (JWTInfo) userDetails;
        String username = getInfoFromToken(token).getUsername();
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }



    public static void main(String[] args) throws Exception {
        String token = generateToken(new JWTInfo("jinghan","123","admin"), 123);

        System.out.println("token: "+token);

        System.out.println("解析："+getInfoFromToken(token).toString());
    }
}
