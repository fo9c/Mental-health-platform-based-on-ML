package domain.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 使用Lombok注解简化代码
@Data
@NoArgsConstructor
@AllArgsConstructor
// 使用TableName注解指定表名
@TableName("user")

// 定义UserPO类,用于映射数据库表
public class UserPO {
    @TableId
    private String id;         // 用户ID

    @TableField(value = "name")
    private String name;         // 用户名

    @TableField(value = "age")
    private int age;             // 用户年龄

    @TableField(value = "password")
    private String password;     // 账号密码

    @TableField(value = "email")
    private String email;        // 用户邮箱

    @TableField(value = "phone")
    private String phone;        // 用户手机号

    @TableField(value = "address")
    private String address;      // 用户地址

    @TableField(value = "role")  // 用户角色
    private String role;

}
