package com.yf.utils.jwt.rsa;

import com.yf.utils.common.StringUtil;
import com.yf.utils.constant.BizConstant;
import com.yf.utils.jwt.JWTInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

/**
 * Created by ace on 2017/9/10.
 *
 *   乙方生成两把密钥（公钥和私钥）
     甲方获取乙方的公钥，然后用它对信息加密。
     乙方得到加密后的信息，用私钥解密，乙方也可用私钥加密字符串
     甲方获取乙方私钥加密数据，用公钥解密
 */
public class JWTHelper {

    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    /**
     * 公钥加密token
     *
     * @param jwtInfo
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generatePubToken(JWTInfo jwtInfo, int expire) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUsername())
                .claim(BizConstant.JWT_KEY_USER_ID.getValue(), jwtInfo.getUserId())
                .claim(BizConstant.JWT_KEY_NAME.getValue(), jwtInfo.getUsername())
                .claim(BizConstant.JWT_PASSWORD.getValue(), jwtInfo.getPassword())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPublicKey(rsaKeyHelper.generatePublicKey()))
                .compact();
        return compactJws;
    }

    /**
     * 私钥加密token
     *
     * @param jwtInfo
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generatePriToken(JWTInfo jwtInfo, int expire) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUsername())
                .claim(BizConstant.JWT_KEY_USER_ID.getValue(), jwtInfo.getUserId())
                .claim(BizConstant.JWT_KEY_NAME.getValue(), jwtInfo.getUsername())
                .claim(BizConstant.JWT_PASSWORD.getValue(), jwtInfo.getPassword())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(rsaKeyHelper.generatePrivateKey()))
                .compact();
        return compactJws;
    }

    /**
     * 私钥解析token
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserPriToken(String token) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPrivateKey(rsaKeyHelper.generatePrivateKey())).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserPubToken(String token) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(rsaKeyHelper.generatePublicKey())).parseClaimsJws(token);
        return claimsJws;
    }
    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static JWTInfo getInfoFromPubToken(String token) throws Exception {
        Jws<Claims> claimsJws = parserPubToken(token);
        Claims body = claimsJws.getBody();
        return new JWTInfo(body.getSubject(), StringUtil.nullToString(body.get(BizConstant.JWT_KEY_USER_ID.getValue())), StringUtil.nullToString(body.get(BizConstant.JWT_KEY_NAME.getValue())));
    }

    /**
     * 私钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static JWTInfo getInfoFromPriToken(String token) throws Exception {
        Jws<Claims> claimsJws = parserPriToken(token);
        Claims body = claimsJws.getBody();
        return new JWTInfo(body.getSubject(), StringUtil.nullToString(body.get(BizConstant.JWT_KEY_USER_ID.getValue())), StringUtil.nullToString(body.get(BizConstant.JWT_KEY_NAME.getValue())));
    }



    public static void main(String[] args) throws Exception {

        //  甲方获取乙方私钥加密数据，用公钥解密
        String priToken = generatePriToken(new JWTInfo("jinghan","123","admin"), 123);

        System.out.println("priToken: "+priToken);

        System.out.println("解析 priToken："+getInfoFromPriToken(priToken).toString());


//        乙方得到加密后的信息，用私钥解密，乙方也可用私钥加密字符串
//        String pubToken = generatePubToken(new JWTInfo("jinghan","123","admin"), 123);
//
//        System.out.println("pubToken: "+pubToken);

        System.out.println("解析 pubToken："+getInfoFromPubToken(priToken).toString());

    }
}
