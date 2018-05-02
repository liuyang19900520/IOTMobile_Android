package com.liuyang19900520.iotmobile_android.model.bean;

import lombok.Data;

@Data
public class LoginUser {

    private String username;
    private String password;
    private String vcode;
    private String vcodeKey;

}
