package com.unicomWeb.model;

import springjdbc.pojo.Customer;

import java.util.List;

/**
 * Created by York on 2016/8/10.
 */

public class DatabaseMeta {
    private int pageSize = 50;
    private int total;
    private int pageCurrent = 1;
    private List<Customer> list;

    private int getTotal() {
        return total;
    }

    private void setTotal(int total) {

        this.total = total;
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        if (pageCurrent > 1) {
            list = list.subList(pageCurrent * pageSize - 1, pageCurrent * (pageSize + 1) - 1);
        }
        this.pageCurrent = pageCurrent;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        if (list.size() > pageSize && list.size() > 0) {
            list = list.subList(0, pageSize - 1);
        }
        total = list.size() / pageSize + list.size() % pageSize > 0 ? 1 : 0;
        this.list = list;
    }
}
