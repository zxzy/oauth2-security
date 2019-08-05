package com.gf.oauth2.common.result;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private Integer status;

    private String msg;

    private T data;

    public BaseResponse(){
        this.status=200;
        this.msg="success";
    }

    public BaseResponse(T data){
        this.data=data;
        this.status=200;
        this.msg="success";
    }
}
