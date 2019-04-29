package com.styd.testcases.shop;

import com.styd.Base.BaseTest;
import com.styd.model.shop.ModifyBrandShopCase;
import com.styd.utils.DatabaseUtil;
import com.styd.utils.HttpClientUtil;
import com.styd.utils.ReadPropertiesFile;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName ModifyShopInBrandCase_001
 * @Description TDD
 * @Author shenzhenghuan
 * @Date 2019/4/29 13:53
 **/
public class ModifyShopInBrandCase_001 extends BaseTest {
    private Logger logger = Logger.getLogger(ModifyShopInBrandCase_001.class);

    @Test
    public void ModifyShopSuccess(){
        ModifyBrandShopCase modifyBrandShopCase = (ModifyBrandShopCase) DatabaseUtil.getTestDataObject("modifybrandshop",1);

        JSONObject params = new JSONObject();
        params.put("shop_name",modifyBrandShopCase.getShopName());
        params.put("province_id",modifyBrandShopCase.getProvinceId());
        params.put("city_id",modifyBrandShopCase.getCityId());
        params.put("district_id",modifyBrandShopCase.getDistrictId());
        params.put("address",modifyBrandShopCase.getAddress());
        params.put("lat",modifyBrandShopCase.getLat());
        params.put("lng",modifyBrandShopCase.getLat());
        params.put("shop_status",modifyBrandShopCase.getShopStatus());
        params.put("email",modifyBrandShopCase.getEmail());


        String[] shopPhones = modifyBrandShopCase.getShopPhones().split("&");

        JSONArray shop_Phones = new JSONArray();
        shop_Phones.put(shopPhones[0]);
        shop_Phones.put(shopPhones[1]);

        params.put("shop_phones",shop_Phones);

        String[] serviceIds = modifyBrandShopCase.getServiceIds().split("&");

        JSONArray service_ids = new JSONArray();
        service_ids.put(serviceIds[0]);
        service_ids.put(serviceIds[1]);

        params.put("service_ids",service_ids);

        JSONArray businessTime = new JSONArray();
        JSONObject businessTime1 = new JSONObject();
        businessTime1.put("week_day",modifyBrandShopCase.getWeekDay());
        businessTime1.put("start_time",modifyBrandShopCase.getStartTime());
        businessTime1.put("end_time",modifyBrandShopCase.getEndTime());
        businessTime.put(businessTime1);

        params.put("business_time",businessTime);

        String result = HttpClientUtil.doPut(ReadPropertiesFile.getUrl("modifybrandshop.uri")+ "/" + modifyBrandShopCase.getShopId(),params);
        logger.info(result);

        JSONObject resultJson = new JSONObject(result);
        Assert.assertEquals(resultJson.get("msg"),modifyBrandShopCase.getExpected());
    }
}
