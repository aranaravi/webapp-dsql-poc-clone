package jsp.springboot.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dsql.DsqlUtilities;

@Configuration(proxyBeanMethods = false)
public class DsqlDataSourceConfig {

    private static final String HOSTNAME =
            "bzt45avdsc6htl334st2krjoee.dsql.us-east-1.on.aws";

    private static final String REGION = "us-east-1";

    @Bean
    public HikariDataSource dataSource() {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(
                "jdbc:postgresql://" + HOSTNAME + ":5432/postgres" +
                        "?currentSchema=webapp_pocdb&ssl=true&sslmode=require"
        );

        config.setUsername("admin");

        //Generate IAM token as password using EC2 IAM Role
        config.setPassword(generateIamToken());

        config.setMaxLifetime(1800000);

        //Handle OCC errors
        config.setExceptionOverrideClassName(
                DsqlExceptionOverride.class.getName()
        );

        return new HikariDataSource(config);
    }

    private String generateIamToken() {
        // Automatically picks up EC2 IAM Role — no credentials needed in code
        DsqlUtilities utilities = DsqlUtilities.builder()
                .region(Region.of(REGION))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        return utilities.generateDbConnectAdminAuthToken(builder ->
                builder.hostname(HOSTNAME)
        );
    }
}