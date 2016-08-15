package springjdbc.util;

import springjdbc.pojo.Operator;

import java.sql.ResultSet;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;

/**
 * Created by York on 2016/8/15.
 */
public class OptResultSet implements RowMapper<Operator>{

    public Operator mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Operator operator = new Operator();
        operator.setOptID(resultSet.getString("OPT_ID"));
        operator.setOptPWD(resultSet.getString("OPT_PW"));
        return operator;
    }

}
