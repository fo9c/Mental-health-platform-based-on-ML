package domain.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 使用Lombok注解简化代码
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
// 定义UserPO类,用于映射数据库表
public class UserPO {

    @TableId
    private String id;                  // 用户ID

    @TableField(value = "name")
    private String name;                // 用户名

    @TableField(value = "age")
    private int age;                    // 用户年龄

    @TableField(value = "password")
    private String password;            // 账号密码

    @TableField(value = "sex")
    private String sex;

    @TableField(value = "create_time")
    private LocalDateTime createTime;   // 创建时间

    @TableField(value = "update_time")
    private LocalDateTime updateTime;   // 更新时间

    @TableField(value = "valid")
    private boolean valid;              // 是否有效

}
