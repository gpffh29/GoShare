package com.GoShare.codefAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import com.GoShare.codefAPI.CodefClientInfo;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ApiService {
    public String getBusinessStatus(String reqIdentity) throws IOException, InterruptedException {
        EasyCodef codef = new EasyCodef();
        codef.setClientInfoForDemo(CodefClientInfo.DEMO_CLIENT_ID, CodefClientInfo.DEMO_CLIENT_SECRET);
        codef.setClientInfo(CodefClientInfo.CLIENT_ID, CodefClientInfo.CLIENT_SECRET);
        codef.setPublicKey(CodefClientInfo.PUBLIC_KEY);

        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("organization", "0004");

        List<HashMap<String, String>> reqIdentityList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> reqIdentityMap = new HashMap<String, String>();
        reqIdentityMap.put("reqIdentity", reqIdentity);
        reqIdentityList.add(reqIdentityMap);

        parameterMap.put("reqIdentityList", reqIdentityList);

        String productUrl = "/v1/kr/public/nt/business/status";


        return codef.requestProduct(productUrl, EasyCodefServiceType.DEMO, parameterMap);
    }
}
