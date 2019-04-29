package com.styd.model;

import lombok.Data;

/**
 * @ClassName LoginCase
 * @Description TDD
 * @Author shenzhenghuan
 * @Date 2019/4/19 19:41
 **/
@Data
public class LoginCase {
    private int id;
    private String name;
    private String password;
    private String expected;
}
