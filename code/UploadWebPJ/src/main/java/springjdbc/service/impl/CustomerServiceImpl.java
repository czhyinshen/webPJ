package springjdbc.service.impl;

import org.springframework.stereotype.Service;
import springjdbc.dao.impl.CustomerDaoImpl;
import springjdbc.pojo.Customer;
import springjdbc.service.CustomerService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by York on 2016/8/9.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource(name = "customerDao")
    private CustomerDaoImpl customerDao;

    public void addUser(Customer[] customer) {
        for (int i = 0; i < customer.length; i++) {
            customerDao.add(customer[i]);
        }
    }

    public List<Customer> quaryAll() {
        return customerDao.quaryAll();
    }

    public int[] update(Customer[] customers) {
        int[] results = new int[customers.length];

        for (int i = 0; i < customers.length; i++) {
            if (quarySome("sn", customers[i].getSn()).size() > 0) {
                results[i] = customerDao.update(customers[i]);
            }else {
                results[i] = customerDao.add(customers[i]);
            }
        }
        return results;
    }

    @Override
    public int delete(Customer[] customers) {
        String[] snArr = new String[customers.length];
        for (int i = 0; i < customers.length; i++) {
            snArr[i] = customers[i].getSn();
        }
        return customerDao.delete(snArr);
    }

    @Override
    public List<Customer> quarySome(String id, String value) {
        return customerDao.quarySome(id, value);
    }

}
