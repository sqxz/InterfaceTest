package com.styd.model.shop;

import lombok.Data;

/**
 * @ClassName ShopListCase
 * @Description TDD
 * @Author shenzhenghuan
 * @Date 2019/4/21 15:51
 **/
@Data
public class ShopListCase {
    private int id;
    private String page;
    private String size;
    private String expected;
}
