package utils.loginUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import domain.user.UserDTO;
import domain.user.UserPO;
import lombok.Getter;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Component
// 用于存放用户的所有鉴权信息
public class UserLoginDetails {
    @Autowired
    private UserMapper userMapper;

    @Getter
    private Boolean valid = false;              // 本类是否有效

    @Getter
    private String message;                     // 登录事件携带的信息

    @Getter
    private UserPO userPO;                      // 用户信息

    @Getter
    private HashMap<String, Object> JWTDetails; // JWTToken中的负载（PAYLOAD）

    /**
     * 构造登录器成功时的返回信息
     * @param userDTO 用户信息
     * return UserLoginDetails 用户登录信息类
     */
    public UserLoginDetails(UserDTO userDTO, HashMap<String, Object> JWTDetails) {
        // 设置本类为有效
        if (isAccountNonExpired() && isTokenNonExpired() && isAccountNonLocked() && isEnabled()) {
            this.valid = true;
        }

        // 建立条件构造器，根据userDTO查询数据库中的用户信息
        LambdaQueryWrapper<UserPO> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(UserPO::getId, userDTO.getId());
        this.userPO = userMapper.selectOne(userQueryWrapper);

        // 设置JWTToken中的负载（PAYLOAD）
        this.JWTDetails = JWTDetails;
    }

    /**
     * 构造登录器失败时的返回信息
     * @param message 错误信息
     */
    public UserLoginDetails(String message) {
        this.message = message;
    }

    /**
     * 构造登录器失败时的返回信息
     */
    public UserLoginDetails() {
    }

    // TODO: Implement the following methods

    // 用户鉴权是否过期
    public boolean isAccountNonExpired() {
        return true;
    }

    // 用户是否被锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    // 用户JWTToken凭据是否过期
    public boolean isTokenNonExpired() {
        if (JWTDetails.containsKey("exp")) {
            LocalDateTime expireTime = LocalDateTime.parse(JWTDetails.get("exp").toString(), DateTimeFormatter.ISO_DATE_TIME);
            return LocalDateTime.now().isBefore(expireTime);
        } else {
            return false;
        }
    }

    // 用户是否可用
    public boolean isEnabled() {
        return userPO.isValid();
    }
}
