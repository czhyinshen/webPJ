package springjdbc.dao;


import springjdbc.pojo.Customer;

import java.util.List;

/**
 * Created by York on 2016/8/9.
 */
public interface CustomerDao {

    int add(Customer customer);

    List<Customer> quaryAll();

    int update(Customer customer);

    int delete(String[] id);

    List<Customer> quarySome(String id ,String value);
}
