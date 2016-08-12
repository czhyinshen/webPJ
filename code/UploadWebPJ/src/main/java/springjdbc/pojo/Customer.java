package springjdbc.pojo;

import org.springframework.stereotype.Component;

/**
 * Created by York on 2016/8/9.
 */
@Component("user")
public class Customer {
    private String sn;
    private String mobile_num;
    private String status;
    private String start_time;
    private String stop_time;
    private String stc_msg;
    private String var_msg;
    private String in_times;
    private String msg_lvl;
    private String opt_id;

    public Customer() {
        super();
    }

    public Customer(String sn, String mobile_num, String status, String start_time, String stop_time, String stc_msg, String var_msg, String in_times, String msg_lvl, String opt_id) {
        this.sn = sn;
        this.mobile_num = mobile_num;
        this.status = status;
        this.start_time = start_time;
        this.stop_time = stop_time;
        this.stc_msg = stc_msg;
        this.var_msg = var_msg;
        this.in_times = in_times;
        this.msg_lvl = msg_lvl;
        this.opt_id = opt_id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMobile_num() {
        return mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public String getStc_msg() {
        return stc_msg;
    }

    public void setStc_msg(String stc_msg) {
        this.stc_msg = stc_msg;
    }

    public String getVar_msg() {
        return var_msg;
    }

    public void setVar_msg(String var_msg) {
        this.var_msg = var_msg;
    }

    public String getIn_times() {
        return in_times;
    }

    public void setIn_times(String in_times) {
        this.in_times = in_times;
    }

    public String getMsg_lvl() {
        return msg_lvl;
    }

    public void setMsg_lvl(String msg_lvl) {
        this.msg_lvl = msg_lvl;
    }

    public String getOpt_id() {
        return opt_id;
    }

    public void setOpt_id(String opt_id) {
        this.opt_id = opt_id;
    }
}
