package com.unicomWeb.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.unicomWeb.model.DatabaseMeta;
import com.unicomWeb.model.ResponseMeta;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springjdbc.pojo.Customer;
import springjdbc.service.CustomerService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * Created by York on 2016/8/10.
 */
@Controller
@RequestMapping("/controller")
public class DataEditController {

    @Resource(name = "customerService")
    private CustomerService customerService;

    @RequestMapping(value = "/quary", method = RequestMethod.GET)
    public
    @ResponseBody
    DatabaseMeta quaryAll(HttpServletRequest request) throws IllegalStateException, IOException {

        Map<String, String> map = request.getParameterMap();
        DatabaseMeta databaseMeta = new DatabaseMeta();
        List<Customer> customers;

        String pageSize = request.getParameter("pageSize");
        String pageCurrent = request.getParameter("pageCurrent");
        String orderField = request.getParameter("orderField");
        String orderDirection = request.getParameter("orderDirection");
        String[] filterFields = request.getParameterValues("filterFields");


        if (orderField != null) {
            customers = customerService.quaryBySort(orderDirection, orderField);

        } else if (filterFields != null) {

            Map<String, String> filterMap = new HashMap<>();
            Map<String, String> operatorMap = new HashMap<>();

            for (int i = 0; i < filterFields.length; i++) {
                filterMap.put(filterFields[i], request.getParameter(filterFields[i]));
                String operator = filterFields[i] + ".operator";
                operatorMap.put(operator, request.getParameter(operator));
            }

            customers = customerService.quarryByFilter(filterMap,operatorMap);
        } else {
            customers = customerService.quaryAll();
        }

        databaseMeta.paging(customers, pageCurrent, pageSize);

        return databaseMeta;
    }

    //添加或删除
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMeta update(HttpServletRequest request, HttpServletResponse response) throws IllegalMappingException {
        String jsonMeta = request.getParameter("json");
        Gson gson = new Gson();
        JSON.parse(jsonMeta).getClass();
        Customer[] customers;
        if (jsonMeta.startsWith("[") && jsonMeta.endsWith("]")) {
            customers = gson.fromJson(jsonMeta, Customer[].class);
        } else {
            Customer customer = gson.fromJson(jsonMeta, Customer.class);
            customers = new Customer[1];
            customers[0] = customer;
        }
        customerService.update(customers);

        ResponseMeta responseMeta = new ResponseMeta();
        responseMeta.setStatusCode("200");
        responseMeta.setMessage("success");
        return responseMeta;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseMeta delete(HttpServletRequest request) throws IllegalMappingException, TimeoutException {
        String jsonMeta = request.getParameter("json");
        Gson gson = new Gson();
        Customer[] customers = gson.fromJson(jsonMeta, Customer[].class);
        ResponseMeta responseMeta = new ResponseMeta();
        responseMeta.setStatusCode(customerService.delete(customers));
        return responseMeta;
    }

    @RequestMapping(value = "/pageChange", method = RequestMethod.POST)
    public
    @ResponseBody
    DatabaseMeta pageChange(HttpServletRequest request) throws IllegalMappingException, TimeoutException {
        String pageCurrent = request.getParameter("pageCurrent");
        DatabaseMeta databaseMeta = new DatabaseMeta();
        databaseMeta.setPageCurrent(Integer.parseInt(pageCurrent));
        databaseMeta.setList(customerService.quaryAll());
        return databaseMeta;
    }

}

