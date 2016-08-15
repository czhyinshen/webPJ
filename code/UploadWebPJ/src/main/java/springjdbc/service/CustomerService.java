package springjdbc.service;

import springjdbc.pojo.Customer;
import springjdbc.pojo.Operator;

import java.util.List;

/**
 * Created by York on 2016/8/9.
 */
public interface CustomerService {
    void addUser(Customer[] customer);

    List<Customer> quaryAll();

    int[] update(Customer[] customers);

    int delete(Customer[] customers);

    List<Customer> quarySome(String id ,String value);


}
