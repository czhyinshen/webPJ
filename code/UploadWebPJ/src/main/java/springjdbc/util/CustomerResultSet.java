package springjdbc.util;
import org.springframework.jdbc.core.RowMapper;
import springjdbc.pojo.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by York on 2016/8/10.
 */
public class CustomerResultSet implements RowMapper<Customer> {

    public Customer mapRow(ResultSet rs, int rowNum)throws SQLException{
        Customer customer = new Customer();
        customer.setOpt_id(rs.getString("OPT_ID"));
        customer.setStatus(rs.getString("STATUS"));
        customer.setMobile_num(rs.getString("MOBILE_NUM"));
        customer.setSn(rs.getString("SN"));
        customer.setIn_times(rs.getString("IN_TIMES"));
        customer.setStart_time(rs.getString("START_TIME"));
        customer.setStop_time(rs.getString("STOP_TIME"));
        return customer;
    }
}
