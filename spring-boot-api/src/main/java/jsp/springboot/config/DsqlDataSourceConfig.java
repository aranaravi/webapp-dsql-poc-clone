package jsp.springboot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DsqlDataSourceConfig {

    @Bean
    public HikariDataSource dataSource(DataSourceProperties properties) {
        HikariDataSource hds = properties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();

        // Prevent HikariCP evicting connections on DSQL OCC errors
        hds.setExceptionOverrideClassName(DsqlExceptionOverride.class.getName());

        return hds;
    }
}