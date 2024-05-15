package utils.loginUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import domain.user.UserDTO;
import domain.user.UserPO;
import lombok.Getter;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 用于存放用户的所有鉴权信息
public class UserLoginDetails {
    @Autowired
    private UserMapper userMapper;

    private Boolean valid;              // 本类是否有效

    @Getter
    private String message;             // 登录事件携带的信息

    @Getter
    private String username;            // 数据库中的用户名

    @Getter
    private String userID;              // 数据库中的用户ID

    @Getter
    private String userRole;            // 数据库中的用户角色



    public UserLoginDetails(UserDTO userDTO) {
        this.valid = true;

        // 建立条件构造器，根据userDTO查询数据库中的用户信息
        LambdaQueryWrapper<UserPO> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(UserPO::getId, userDTO.getId());
        UserPO userPO = userMapper.selectOne(userQueryWrapper);
        this.username = userPO.getName();
        this.userRole = userPO.getRole();
        this.userID = userPO.getId();
    }

    public UserLoginDetails(String message) {
        this.message = message;
        this.valid = false;
    }

    public UserLoginDetails() {
        this.valid = false;
    }


    
    // TODO: Implement the following methods



//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    public boolean isEnabled() {
//        return true;
//    }
}
