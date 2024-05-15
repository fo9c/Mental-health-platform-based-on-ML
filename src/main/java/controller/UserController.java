package controller;

import domain.user.UserDTO;
import domain.user.UserVO;
import org.springframework.web.bind.annotation.*;
import utils.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseResult<UserVO> login(@RequestHeader("Authorization") String JWTToken,
                                        @RequestBody UserDTO userDTO){

        // TODO
        return null;
    }
}
