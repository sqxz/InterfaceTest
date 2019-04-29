package com.styd.testcases.shop;

import com.styd.Base.BaseTest;
import com.styd.model.shop.ShopListCase;
import com.styd.utils.DatabaseUtil;
import com.styd.utils.HttpClientUtil;
import com.styd.utils.ReadPropertiesFile;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GetShopListCase_001
 * @Description 获取门店列表
 * @Author shenzhenghuan
 * @Date 2019/4/21 15:54
 **/
public class GetShopListCase_001 extends BaseTest {
    private Logger logger = Logger.getLogger(GetShopListCase_001.class);

    @Test(description = "获取门店列表")
    public void getShopListSuccess(){
        ShopListCase shopListCase = (ShopListCase) DatabaseUtil.getTestDataObject("shoplist",2);

        Map<String,String> params = new HashMap<String, String>();
        params.put("page",shopListCase.getPage());
        params.put("size",shopListCase.getSize());

        String result = HttpClientUtil.doGet(ReadPropertiesFile.getUrl("get_shoplist.uri"),params);
        JSONObject object = new JSONObject(result);
        int resultArray =
        object.getJSONObject("data").getJSONArray("shop_info").length();
        logger.info(result);
        Assert.assertEquals(String.valueOf(resultArray),shopListCase.getExpected());
    }
}
