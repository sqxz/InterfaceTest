package com.styd.testcases.card;

import com.styd.model.card.CreatBrandCardsCase;
import com.styd.utils.DatabaseUtil;
import org.json.JSONObject;
import org.testng.annotations.Test;

/**
 * @ClassName CreatBrandCardsCase_001
 * @Description 品牌下新增会员卡
 * @Author shenzhenghuan
 * @Date 2019/4/24 20:23
 **/
public class CreatBrandCardsCase_001 {
    @Test
    public void creatBrandCardsCaseSuccess(){
        CreatBrandCardsCase creatBrandCardsCase = (CreatBrandCardsCase) DatabaseUtil.getTestDataObject("creatBrandCardsCase",1);
        JSONObject params = new JSONObject();
        params.put("card_type", creatBrandCardsCase.getCardType());
        params.put("card_name",creatBrandCardsCase.getCardName());
        params.put("admission_range",creatBrandCardsCase.getAdmissionRange());
        params.put("price_setting",creatBrandCardsCase.getPriceSetting());
    }
}
