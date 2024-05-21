package controller;

import domain.user.UserDTO;
import domain.user.UserPO;
import domain.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import utils.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     */
    @PostMapping("/login")
    public ResponseResult<Object> login(@RequestHeader("Authorization") String JWTToken,
                                        @RequestBody UserDTO userDTO){
        UserPO userPO = userService.getUserInfoByID(userDTO, JWTToken);
        if (userPO != null) {
            UserVO userVO = UserVO.builder()
                    .id(userPO.getId())
                    .name(userPO.getName())
                    .sex(userPO.getSex())
                    .build();
            return ResponseResult.success("获取成功", userVO);
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 注册新用户
     * @return boolean 是否注册成功
     */
    @PostMapping("/register")
    public ResponseResult<Object> register(){
        if (userService.userRegister()) {
            return ResponseResult.success("注册成功");
        } else {
            return ResponseResult.error("注册失败");
        }
    }

}
