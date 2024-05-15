package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import domain.user.UserPO;

// 继承BaseMapper接口,泛型指定实体类,简化CRUD操作
public interface UserMapper extends BaseMapper<UserPO> {
}
