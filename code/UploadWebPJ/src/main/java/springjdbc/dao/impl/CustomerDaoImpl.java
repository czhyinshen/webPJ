package springjdbc.dao.impl;

import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springjdbc.dao.CustomerDao;
import springjdbc.pojo.Customer;
import springjdbc.util.CustomerResultSet;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLTimeoutException;
import java.util.List;

/**
 * Created by York on 2016/8/9.
 */
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

    private final String chartName = "UNICOM_MGR_SYS_DB";

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public int add(Customer customer) {
        int result = 0;
        String sql = "INSERT INTO " + chartName + "(SN,MOBILE_NUM,STATUS,IN_TIMES,OPT_ID) VALUES(?,?,?,?,?)";
        System.out.println(sql);
        try {
            System.out.println(customer.getIn_times());
            result = jdbcTemplate.update(sql, customer.getSn(), customer.getMobile_num(), customer.getStatus(), customer.getIn_times(), customer.getOpt_id());
            System.out.println(result);
        } catch (QueryTimeoutException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Customer> quaryAll() {
        String sql = "SELECT * FROM " + chartName;
        jdbcTemplate.setQueryTimeout(1000 * 10);

        List<Customer> list = null;
        try {
            list = jdbcTemplate.query(sql, new CustomerResultSet());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Customer> quarySome(String id, String value) {
        String sql = "SELECT  * FROM " + chartName + " where " + id + " = " + value;
        List<Customer> list = null;
        try {
            list = jdbcTemplate.query(sql, new CustomerResultSet());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int update(Customer customer) {

        Field[] fields = customer.getClass().getDeclaredFields();
        int result = 0;
        for (int i = 0; i < fields.length; i++) {
            String sql = "UPDATE " + chartName + " SET " + fields[i].getName() + " = '" + getFieldValueByName(fields[i].getName(), customer) + "'" + " WHERE SN = '" + customer.getSn() + "'";

            try {
                result = jdbcTemplate.update(sql);

            } catch (QueryTimeoutException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int delete(String[] id) {
        int result = 0;
        for (int i = 0; i < id.length; i++) {
            String sql = "DELETE FROM " + chartName + " where SN = " + id[i];
            try {
                result = jdbcTemplate.update(sql);
            } catch (QueryTimeoutException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    //获取对象属性值
    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {

            return null;
        }
    }

}
