package jsp.springboot.config;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rds.RdsUtilities;

@Component
public class IamTokenUtil {

    public String generateToken() {
        RdsUtilities utilities = RdsUtilities.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        return utilities.generateAuthenticationToken(r -> r
                .hostname("bzt45avdsc6htl334st2krjoee.dsql.us-east-1.on.aws")
                .port(5432)
                .username("admin")
        );
    }
}