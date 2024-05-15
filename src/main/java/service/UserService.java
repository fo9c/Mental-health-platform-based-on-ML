package service;

import org.springframework.transaction.annotation.Transactional;


// 使用Transactional注解声明这个类的所有方法都需要事务管理,在抛出异常时会回滚
@Transactional
public interface UserService {
}
