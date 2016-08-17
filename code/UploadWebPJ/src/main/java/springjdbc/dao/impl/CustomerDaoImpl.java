package springjdbc.dao.impl;

import com.sun.javafx.collections.MappingChange;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        int sysResult;
        insertSysDate(customer);
        String sql = "INSERT INTO " + chartName + "(SN,MOBILE_NUM,STATUS,IN_TIMES,OPT_ID) VALUES(?,?,?,?,?)";
        try {
            result = jdbcTemplate.update(sql, customer.getSn(), customer.getMobile_num(), customer.getStatus(), customer.getIn_times(), customer.getOpt_id());
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
        if (Objects.equals(value, "")) {
            return null;
        }
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

    //获取数据库时间
    private void insertSysDate(Customer customer) {
        String sysDateSql = "SELECT sysdate DBdate FROM dual";
        Map<String, Object> map = jdbcTemplate.queryForMap(sysDateSql);
        Timestamp ts = (Timestamp) map.get("DBdate");
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String tsStr = "";
        int x = (int) (Math.random() * 1000);
        try {
            tsStr = sdf.format(ts) + x;
            customer.setSn(tsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> quaryBySort(String dir, String value) {
        String sql = null;
        sql = "SELECT * FROM " + chartName + " ORDER BY " + value + " " + dir;
        jdbcTemplate.setQueryTimeout(1000 * 10);
        List<Customer> list = null;
        System.out.println(sql);
        try {
            list = jdbcTemplate.query(sql, new CustomerResultSet());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Customer> quarryByFilter(Map valueMap,Map operatorMap) {
        String sql = null;
        Set valueSet = valueMap.keySet();
        Set operatorSet = operatorMap.keySet();
        Iterator valueIt = valueSet.iterator();
        Iterator operatorIt = operatorSet.iterator();

        String values = " ";
        String operatorName = "";
        String pattern = "";
        while (valueIt.hasNext()) {
            String value = (String) valueIt.next();
            pattern = (String) valueMap.get(value);
            String operator = (String)operatorIt.next();
            operatorName = (String) operatorMap.get(operator);
            values = value ;
            System.out.println("value:"+value+"pattern:"+pattern+"opeN："+operatorName);
        }

        sql = "SELECT * " + " FROM " + chartName + " WHERE " + values + " " + operatorName +" "+ pattern;
        List<Customer> list = null;
        System.out.println("values:"+values+sql);
        try {
            list = jdbcTemplate.query(sql, new CustomerResultSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
