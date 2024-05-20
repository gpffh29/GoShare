package com.GoShare.codefAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import com.GoShare.codefAPI.CodefClientInfo;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ApiService {

    //운전면허 진위여부 API Endpoint
    String productUrl = "/v1/kr/public/ef/driver-license/status";

    //운전 면허 진위 여부를 위한 정보를 가져오는 메서드
    public HashMap<String, Object> Driver_License(ApiDto apiDto){
        EasyCodef codef = new EasyCodef();
        codef.setClientInfoForDemo(CodefClientInfo.DEMO_CLIENT_ID, CodefClientInfo.DEMO_CLIENT_SECRET);
        codef.setClientInfo(CodefClientInfo.CLIENT_ID, CodefClientInfo.CLIENT_SECRET);
        codef.setPublicKey(CodefClientInfo.PUBLIC_KEY);

        String result;

        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("organization", "0001");
        parameterMap.put("loginType", "5");
        parameterMap.put("loginUserName",apiDto.getLoginUserName());
        parameterMap.put("identity", apiDto.getIdentity());
        parameterMap.put("loginTypeLevel","1");
        parameterMap.put("phoneNo",apiDto.getPhoneNo());
        parameterMap.put("birthDate",apiDto.getBirthDate());
        parameterMap.put("licenseNo01",apiDto.getLicenseNo01());
        parameterMap.put("licenseNo02",apiDto.getLicenseNo02());
        parameterMap.put("licenseNo03",apiDto.getLicenseNo03());
        parameterMap.put("licenseNo04",apiDto.getLicenseNo04());
        parameterMap.put("serialNo",apiDto.getSerialNo());
        parameterMap.put("userName",apiDto.getLoginUserName());

        try {
            result = codef.requestProduct(productUrl, EasyCodefServiceType.DEMO, parameterMap);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, Object> responseMap = null;
        try {
            responseMap = new ObjectMapper().readValue(result, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, Object> dataMap = (HashMap<String, Object>)responseMap.get("data");

        HashMap<String, Object> twoWayInfo = new HashMap<>();
        twoWayInfo.put("jobIndex",(int)dataMap.get("jobIndex"));
        twoWayInfo.put("threadIndex",(int)dataMap.get("threadIndex"));
        twoWayInfo.put("jti",dataMap.get("jti"));
        twoWayInfo.put("twoWayTimestamp",(Long)dataMap.get("twoWayTimestamp"));
        parameterMap.put("twoWayInfo", twoWayInfo);
        parameterMap.put("is2Way", true);



        return parameterMap;
    }

    public String TwoWay(HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
        /** #1.쉬운 코드에프 객체 생성 및 클라이언트 정보 설정 */
        EasyCodef codef = new EasyCodef();
        codef.setClientInfoForDemo(CodefClientInfo.DEMO_CLIENT_ID, CodefClientInfo.DEMO_CLIENT_SECRET);
        codef.setClientInfo(CodefClientInfo.CLIENT_ID, CodefClientInfo.CLIENT_SECRET);
        codef.setPublicKey(CodefClientInfo.PUBLIC_KEY);

/** #2.추가인증 입력부 파라미터 설정 */
//간편인증 추가인증 입력부
        parameterMap.put("is2Way", true);

// 요청 Endpoint는 동일함
        String result;

// 추가인증 요청 시에는 이지코드에프.requestCertification 으로 호출
        result = codef.requestCertification(productUrl, EasyCodefServiceType.DEMO, parameterMap);

/** #4.결과값 확인 */
        System.out.println("요청A(추가인증) result : " + result);
        return result;
    }
}
