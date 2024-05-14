package cn.fo9c.mentalhealthplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan("mapper")
public class MentalHealthPlatformBasedOnLvCvcgmApplication {

    public static void main(String[] args) {

        // 新启动项追加路径显示
        ConfigurableApplicationContext application = SpringApplication.run(MentalHealthPlatformBasedOnLvCvcgmApplication.class, args);
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        String property = env.getProperty("server.servlet.context-path");
        String path = property == null ? "" :  property;
        System.out.println(
                "———————————————————————————————————————————————————————————————————————————————————————————————————————————\n\t" +
                        "         Access URLs:\thttp://localhost:" + port + path + "              SpringBoot   Version: 3.2.4 \n\t" +
                        "         DataBase: MySQL                                   Mybatis-Plus Version: 3.5.5\n" +
                        "———————————————————————————————————————————————————————————————————————————————————————————————————————————");
    }
}