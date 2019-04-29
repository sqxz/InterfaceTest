package com.styd.model.shop;

import lombok.Data;

/**
 * @ClassName ModifyBrandShopCase
 * @Description TDD
 * @Author shenzhenghuan
 * @Date 2019/4/29 11:23
 **/
@Data
public class ModifyBrandShopCase {
    private int id;
    private int shopId;
    private String shopName;
    private int provinceId;
    private int cityId;
    private int districtId;
    private String address;
    private String lat;
    private String lng;
    private String shopPhones;
    private int shopStatus;
    private String serviceIds;
    private String businessTime;
    private int weekDay;
    private String startTime;
    private String endTime;
    private String email;
    private String shopCoverImage;
    private String expected;
}
