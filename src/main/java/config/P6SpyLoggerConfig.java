package config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6SpyLoggerConfig implements MessageFormattingStrategy {

    /**
     * 该文件为P6Spy配置文件，用于监控SQL执行情况
     *
     * @param connectionId: 连接ID
     * @param now:          当前时间
     * @param elapsed:      花费时间
     * @param category:     类别
     * @param prepared:     预编译SQL
     * @param sql:          最终执行的SQL
     * @param url:          数据库连接地址
     * @return 格式化日志结果
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? "| Execution time: " + now + // 执行时间
                " | Takes "  + elapsed + " ms" +                         // SQL语句耗时
                " | SQL statement: " +                                   // SQL语句
                sql.replaceAll("[\\s]+", " ") + " |ᨐᵐᵉᵒʷ" : "";
    }
}
