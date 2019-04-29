package com.styd.model.card;

import lombok.Data;

/**
 * @ClassName CreatBrandCardsCase_001
 * @Description 创建品牌下的门店
 * @Author shenzhenghuan
 * @Date 2019/4/24 20:08
 **/
@Data
public class CreatBrandCardsCase {
    private int id;
    private int cardType;
    private String cardName;
    private int admissionRange;
    private int priceSetting;
    private int supportSales;
    private String startTime;
    private String endTime;
    private int isTransfer;
    private int unit;
    private int num;
    private int sellType;
    private String cardIntroduction;
    private String cardContents;
    private String cardBg;
    private int publishChannel;
    private String admissionShopList;
    private String sellShopList;
    private String priceGradient;
    private int priceGradientUnit;
    private int priceGradientNum;
    private int priceGradientRally_price;
    private int priceGradientMin_price;
    private int priceGradientMaxPrice;
    private int priceGradientFrozenDay;
    private int priceGradientGiftUnit;
}
