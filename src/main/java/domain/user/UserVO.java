package domain.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 该注解表示返回的JSON数据中,不包含NULL的字段
@JsonInclude(JsonInclude.Include.NON_NULL)

// 定义VO类,该类是用于返回前端进行展示的类(回传)
public class UserVO {

    private String id;                // 用户ID

    private String name;              // 用户名

    private int age;                  // 用户年龄

    private String sex;               // 用户性别

}
