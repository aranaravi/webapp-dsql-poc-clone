package jsp.springboot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dsql.DsqlUtilities;

@Configuration
public class DataSourceConfig {

    private static final String HOST =
            "bzt45avdsc6htl334st2krjoee.dsql.us-east-1.on.aws";

    @Bean
    public HikariDataSource dataSource() {

        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(
                "jdbc:postgresql://" + HOST + ":5432/postgres" +
                        "?currentSchema=webapp_pocdb&ssl=true&sslmode=require"
        );

        ds.setUsername("admin");

        // ✅ IAM token
        DsqlUtilities util = DsqlUtilities.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        String token = util.generateDbConnectAdminAuthToken(b ->
                b.hostname(HOST)
        );

        ds.setPassword(token);

        return ds;
    }
}