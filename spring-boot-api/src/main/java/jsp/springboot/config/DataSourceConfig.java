package jsp.springboot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    private final IamTokenUtil tokenUtil;

    public DataSourceConfig(IamTokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Bean
    public DataSource dataSource() {

        HikariDataSource hikari = new HikariDataSource();

        hikari.setMaximumPoolSize(5);
        hikari.setMinimumIdle(0);

        // ✅ IMPORTANT: rotate before token expiry (15 min)
        hikari.setMaxLifetime(840000); // 14 min
        hikari.setIdleTimeout(600000);

        hikari.setDataSource(new PGSimpleDataSource() {
            {
                setURL("jdbc:postgresql://publicip.dsql.us-east-1.on.aws:5432/postgres?currentSchema=webapp_pocdb&sslmode=require");
                setUser("admin");
            }

            @Override
            public Connection getConnection() throws SQLException {
                return super.getConnection("admin", tokenUtil.generateToken());
            }
        });

        return hikari;
    }
}