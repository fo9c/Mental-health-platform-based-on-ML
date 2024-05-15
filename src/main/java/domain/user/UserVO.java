package domain.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 使用Lombok注解简化代码
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// 该注解表示返回的JSON数据中,不包含NULL的字段
@JsonInclude(JsonInclude.Include.NON_NULL)
// 定义VO类,该类是用于返回前端进行展示的类(回传)
public class UserVO {

    private Integer id;

    private String name;

    private int age;

}
