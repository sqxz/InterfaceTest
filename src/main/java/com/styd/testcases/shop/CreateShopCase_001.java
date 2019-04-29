package com.styd.testcases.shop;

import com.styd.Base.BaseTest;
import com.styd.model.shop.CreatShopCase;
import com.styd.utils.DatabaseUtil;
import com.styd.utils.HttpClientUtil;
import com.styd.utils.ReadPropertiesFile;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @ClassName CreateShopCase_001
 * @Description TDD
 * @Author shenzhenghuan
 * @Date 2019/4/28 18:59
 **/
public class CreateShopCase_001 extends BaseTest {
    private Logger logger = Logger.getLogger(CreateShopCase_001.class);

    @Test()
    public void  createShopSuccess(){
        CreatShopCase creatShopCase = (CreatShopCase) DatabaseUtil.getTestDataObject("createshop",1);
        JSONObject params = new JSONObject();
        params.put("shop_name",creatShopCase.getShopName());
        params.put("province_id",creatShopCase.getProvinceId());
        params.put("city_id",creatShopCase.getCityId());
        params.put("district_id",creatShopCase.getDistrictId());
        params.put("address",creatShopCase.getAddress());
        params.put("lat",creatShopCase.getLat());
        params.put("lng",creatShopCase.getLng());
        params.put("shop_status",creatShopCase.getShopStatus());
        params.put("shop_cover_image",creatShopCase.getShopCoverImage());
        params.put("email",creatShopCase.getEmail());

        JSONArray phoneArrary = new JSONArray();
        logger.info("门店号码："+creatShopCase.getShopPhones().split(",")[0]);

        phoneArrary.put(creatShopCase.getShopPhones().split("&")[0]);
        phoneArrary.put(creatShopCase.getShopPhones().split("&")[1]);

        logger.info("电话数组"+phoneArrary);
        params.put("shop_phones",phoneArrary);

        JSONArray serviceIds = new JSONArray();
        serviceIds.put(creatShopCase.getServiceIds().split("&")[0]);
        serviceIds.put(creatShopCase.getServiceIds().split("&")[1]);

        params.put("service_ids",serviceIds);

        JSONArray businessTime = new JSONArray();
        JSONObject businessTime1 = new JSONObject();
        businessTime1.put("week_day",creatShopCase.getWeekDay());
        businessTime1.put("start_time",creatShopCase.getStartTime());
        businessTime1.put("end_time",creatShopCase.getEndTime());
        businessTime.put(businessTime1);

        params.put("business_time",businessTime);
        String result = HttpClientUtil.doPost(ReadPropertiesFile.getUrl("create_shop.uri"),params);

        JSONObject resultObject = new JSONObject(result);
        logger.info(result);

        Assert.assertEquals(resultObject.get("msg"),creatShopCase.getExpected());
    }
}
