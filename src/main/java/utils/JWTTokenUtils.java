package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 本项目内JWT-Token设置的负载（PAYLOAD）默认格式如下：
 * {
 *   "claims": {
 *     "role": "admin",
 *     "id": "a6d53373-c6fd-44ee-b177-5ceae0e18a92"
 *   },
 *   "exp": 1714236414
 * }
 */

public class JWTTokenUtils {
    // 设置JWT秘钥
    private static final String secret = "fo9c.3a";
    // 设置JWT过期时间（7天）
    private static final long expireTime = 1000L * 60 * 60 * 24 * 7;
    // 设置日志记录器
    private static final Logger JWTlogger = LoggerFactory.getLogger(JWTTokenUtils.class);

    /**
     * 生成新的JWTToken
     * @param claims 负载（PAYLOAD）
     * @return 新的JWTToken
     */
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                // 添加负载（PAYLOAD）
                .withClaim("claims", claims)
                // 设置过期时间（7天）
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                // 对JWTToken进行签名(JWT库会自动将Header和Payload部分进行Base64Url编码)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 从JWTToken中解析出负载（PAYLOAD）
     * @param token 待验证的JWTToken
     * @return JWTToken中的负载（PAYLOAD）
     */
    public static HashMap<String, Object> parseToken(String token) {
        try {
            // 创建算法实例，用于对JWT进行解码和验证
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
            // 获取JWT的负载（PAYLOAD）作为HashMap集合返回
            return (HashMap<String, Object>) decodedJWT.getClaim("claims").asMap();
        } catch (JWTVerificationException e) {
            // JWT签名不正确或者JWT已经过期时抛出异常
            JWTlogger.error("JWTToken验证失败...");
            return new HashMap<>();
        }
    }
}


/*
JWTToken类是一个工具类，用于生成和解析 JWT-Token,下面是 JWT.Create() 方法的一些常用方法：
.withClaim(String name, Object value):          这个方法用于向 JWT 载荷（Payload）中添加自定义的声明（Claim）。
.withIssuer(String iss):                        设置 JWT 的发行者（Issuer）信息，参数 iss 就是发行者的信息。
.withSubject(String sub):                       设置 JWT 的主题（Subject）信息，参数 sub 就是主题的信息。
.withAudience(String... aud):                   设置 JWT 的接收方（Audience）信息，参数 aud 是接收方的信息列表。
.withExpiresAt(Date exp):                       设置 JWT 的过期时间（Expiration time），参数 exp 是一个 Date 对象，代表了 JWT 的具体过期时间。
.withNotBefore(Date nbf):                       设置 JWT 的生效时间（Not Before），参数 nbf 是一个 Date 对象，代表了 JWT 不能在此时间之前使用。
.withIssuedAt(Date iat):                        设置 JWT 的发行时间（Issued at），参数 iat 是一个 Date 对象，代表了 JWT 的发行时间。
.withJWTId(String jti):                         设置 JWT 的唯一标识（JWT ID），参数 jti 就是此标识的字符串形式。
.withArrayClaim(String name, Object[] values):  添加一个数组类型的 Claim。
.sign(Algorithm algorithm):                     对 JWT 进行签名，参数 algorithm 是具体的签名算法对象。
 */