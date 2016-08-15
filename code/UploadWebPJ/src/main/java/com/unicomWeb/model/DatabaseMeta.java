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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {

        this.total = total;
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
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

        this.list = list;
    }

    public void paging(List<Customer> list, String pageCurrent, String pageSize) {
        int parPageSize = this.pageSize;
        int parPageCurrent = this.pageCurrent;

        if (pageCurrent != null) {
            parPageSize=  Integer.parseInt(pageSize);
            this.setPageSize(parPageSize);
        }

        if (pageCurrent != null) {
            parPageCurrent = Integer.parseInt(pageCurrent);
            this.setPageCurrent(parPageCurrent);
        }

        total = list.size() ;
        if (list.size() > parPageSize) {
            int start = (parPageCurrent - 1) * parPageSize;
            int end = parPageCurrent * parPageSize;
            list = list.subList(start, end > list.size() ? list.size() : end);
        }
        this.setList(list);

    }
}
