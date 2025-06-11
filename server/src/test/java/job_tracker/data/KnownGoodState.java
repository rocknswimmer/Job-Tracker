package job_tracker.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;

@Component
public class KnownGoodState {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private boolean hasRun = false;

    public void set() {
        jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("call set_known_good_state()")) {
                return cs.execute();
            }
        });
    }

    public void reset() {
        hasRun = false;
    }
}
