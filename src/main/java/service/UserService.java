package service;

import domain.user.UserDTO;
import domain.user.UserPO;
import org.springframework.transaction.annotation.Transactional;


// 使用Transactional注解声明这个类的所有方法都需要事务管理,在抛出异常时会回滚
@Transactional
public interface UserService {
    /**
     * 查询某用户所有信息
     * @param userDTO 用户信息
     * @return UserVO 用户信息
     */
    UserPO getUserInfo(UserDTO userDTO, String JWTToken);
}
