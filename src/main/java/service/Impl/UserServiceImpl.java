package service.Impl;

import domain.user.UserDTO;
import domain.user.UserPO;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LoginService;
import service.UserService;
import utils.loginUtils.UserLoginDetails;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginService loginService;

    @Override
    public UserPO getUserInfo(UserDTO userDTO, String JWTToken) {
        // 验证登录信息
        UserLoginDetails userLoginDetails = loginService.loginAuthenticator(JWTToken, userDTO);

        if (userLoginDetails.getValid()) {
            // 查询用户信息
            return userMapper.selectById(userDTO.getId());
        } else {
            return null;
        }
    }
}
