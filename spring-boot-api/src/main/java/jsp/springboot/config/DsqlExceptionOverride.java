package jsp.springboot.config;

import com.zaxxer.hikari.SQLExceptionOverride;
import java.sql.SQLException;

public class DsqlExceptionOverride implements SQLExceptionOverride {

    private static final String OCC_ERROR_CODE = "40001";

    // ✅ Use SQLExceptionOverride.Override instead of just Override
    public SQLExceptionOverride.Override adjudicate(SQLException e) {
        if (OCC_ERROR_CODE.equals(e.getSQLState())) {
            return SQLExceptionOverride.Override.DO_NOT_EVICT;
        }
        return SQLExceptionOverride.Override.CONTINUE_EVICT;
    }
}