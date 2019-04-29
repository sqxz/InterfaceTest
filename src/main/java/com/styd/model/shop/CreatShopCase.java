package com.styd.model.shop;

import lombok.Data;

/**
 * @ClassName CreatShopCase
 * @Description TDD
 * @Author shenzhenghuan
 * @Date 2019/4/28 18:41
 **/
@Data
public class CreatShopCase {
    private int id;
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
