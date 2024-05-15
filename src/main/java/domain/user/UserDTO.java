package domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 使用Lombok注解简化代码
@Data
@NoArgsConstructor
@AllArgsConstructor

// 定义DTO类,是用于接收前端传来的数据(接收)
public class UserDTO {

    private Integer id;

    private String name;

    private int age;

}
