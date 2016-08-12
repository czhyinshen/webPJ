package com.unicomWeb.model;


/**
 * Created by York on 2016/8/5.
 */

public class ResponseMeta {

    private String statusCode;
    private String message;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusCode(StatusCode code){
        switch (code) {
            case OK :
                this.statusCode = "200";
                break;
            case TIMEOUT:
                this.statusCode = "301";
                break;
            case ERROR:
                this.statusCode = "300";
        }
    }

    public void setStatusCode(int code) {
        if (code>0){
            this.statusCode = "200";
            message = "success";
        }else {
            this.statusCode = "300";
            message = "failed";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
