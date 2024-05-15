package service;

import domain.user.UserDTO;
import org.springframework.transaction.annotation.Transactional;
import utils.loginUtils.UserLoginDetails;

import java.util.HashMap;
import java.util.Objects;

// 使用Transactional注解声明这个类的所有方法都需要事务管理,在抛出异常时会回滚
@Transactional
public interface LoginService {
    /**
     * 登录身份验证器
     * @param JwtToken Authorization中携带的JWTToken
     * @param userDTO 用户传输对象
     * @return Map
     */
    UserLoginDetails loginAuthenticator(String JwtToken, UserDTO userDTO);
}
