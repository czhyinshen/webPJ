package springjdbc.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springjdbc.pojo.Operator;
import springjdbc.util.OptResultSet;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by York on 2016/8/15.
 */
@Repository(value = "loginDao")
public class LoginDaoImpl {
    private final String charName = "UNICOM_SYS_OPT_DB";

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public Operator getOpt(String optName) {
        String sql = "SELECT * FROM " + charName + " where opt_id = " + "'" +  optName + "'";
        List<Operator> list = null;

        try {
            list = jdbcTemplate.query(sql, new OptResultSet());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.get(0);
    }
}
