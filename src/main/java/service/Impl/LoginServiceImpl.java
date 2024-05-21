package service.Impl;

import domain.user.UserDTO;
import org.springframework.stereotype.Service;
import service.LoginService;
import utils.JWTTokenUtils;
import utils.loginUtils.UserLoginDetails;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

    /**
     * 本项目内JWT-Token设置的负载（PAYLOAD）默认格式如下：
     * {
     *   "claims": {
     *     "role": "admin",
     *     "id": "a6d53373-c6fd-44ee-b177-5case0e18a92"
     *   },
     *   "exp": 1714236414
     * }
     */
    @Override
    public UserLoginDetails loginAuthenticator(String JwtToken, UserDTO userDTO) {

        // 创建一个UserDetails对象，用于存放JWTToken中包含的所有用户信息
        HashMap<String, Object> JWTDetails = new HashMap<>();

        // 解析JWT Token
        HashMap<String, Object> JWTClaim;
        try {
            JWTClaim = JWTTokenUtils.parseToken(JwtToken);
        } catch (Exception e) {
            return new UserLoginDetails("JWTToken解析失败");
        }

        // 获取 JWT过期时间 和 用户ID
        if (JWTClaim.containsKey("exp") &&
                JWTClaim.containsKey("Claims") &&
                JWTClaim.containsKey("id")) {
            JWTDetails.put("expireTime", JWTClaim.get("exp"));
        } else {
            return new UserLoginDetails("JWTToken中缺少必要信息");
        }

        // 检验JWTToken是否过期，是否为当前用户
        LocalDateTime now = LocalDateTime.now();
        if (userDTO.getId().equals(JWTDetails.get("userID")) &&
                now.isAfter((LocalDateTime) JWTDetails.get("expireTime"))) {
            return new UserLoginDetails(userDTO, JWTDetails);
        } else {
            return new UserLoginDetails("JWTToken已过期或不属于当前用户");
        }

    }

}
