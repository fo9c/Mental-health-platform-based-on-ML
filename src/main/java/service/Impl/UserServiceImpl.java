package service.Impl;

import domain.user.UserDTO;
import domain.user.UserPO;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LoginService;
import service.UserService;
import utils.loginUtils.UserLoginDetails;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginService loginService;


    /**
     * 查询某用户所有信息
     * @param userDTO 用户信息
     * @return UserVO 用户信息
     */
    @Override
    public UserPO getUserInfoByID(UserDTO userDTO, String JWTToken) {
        // 验证登录信息
        UserLoginDetails userLoginDetails = loginService.loginAuthenticator(JWTToken, userDTO);

        if (userLoginDetails.getValid()) {
            // 查询用户信息
            return userMapper.selectById(userDTO.getId());
        } else {
            return null;
        }
    }

    /**
     * 注册
     * @return boolean 是否注册成功
     */
    @Override
    public boolean userRegister() {
        // 生成一个新的用户ID
        UUID userID = UUID.randomUUID();

        if (userMapper.selectById(userID) == null) {
            // 插入新用户
            UserPO userPO = new UserPO();
            userPO.setId(String.valueOf(userID));
            userPO.setName("新用户");
            userPO.setPassword("123456");
            userPO.setSex("未知");
            userMapper.insert(userPO);
            return true;
        } else {
            return false;
        }

    }
}
