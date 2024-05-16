package com.GoShare.codefAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import com.GoShare.codefAPI.CodefClientInfo;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class ApiService {
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode getBusinessStatus(String reqIdentity) throws IOException, InterruptedException {
        EasyCodef codef = new EasyCodef();
        codef.setClientInfoForDemo(CodefClientInfo.DEMO_CLIENT_ID, CodefClientInfo.DEMO_CLIENT_SECRET);
        codef.setClientInfo(CodefClientInfo.CLIENT_ID, CodefClientInfo.CLIENT_SECRET);
        codef.setPublicKey(CodefClientInfo.PUBLIC_KEY);

        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("organization", "0004");

        HashMap<String, String> reqIdentityMap = new HashMap<>();
        reqIdentityMap.put("reqIdentity", reqIdentity);

        parameterMap.put("reqIdentityList", List.of(reqIdentityMap));

        String productUrl = "https://api.codef.io/v1/kr/public/nt/business/status";
        String result = codef.requestProduct(productUrl, EasyCodefServiceType.SANDBOX, parameterMap);

        return objectMapper.readTree(result);
    }
}
