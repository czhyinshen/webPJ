package springjdbc.service.impl;

import org.springframework.stereotype.Service;
import springjdbc.dao.impl.LoginDaoImpl;
import springjdbc.pojo.Operator;
import springjdbc.service.CustomerService;
import springjdbc.service.LoginService;

import javax.annotation.Resource;

/**
 * Created by York on 2016/8/15.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService{
    @Resource(name = "loginDao")
    private LoginDaoImpl loginDao;

    public Operator quaryOPT(String id){
        return loginDao.getOpt(id);
    }
}
