package com.GoShare.service;

import com.GoShare.dto.CarInputDto;
import com.GoShare.codefAPI.CodefClientInfo;
import com.GoShare.dto.LicenseInputDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Service
public class ApiService {
    /**운전 면허 진위 여부를 위한 정보를 가져오는 메서드**/
    public HashMap<String, Object> Driver_License(LicenseInputDto licenseInputDto){
        //codef 객체 생성 및 key값, endpoint URL 설정
        String productUrl = "/v1/kr/public/ef/driver-license/status";
        EasyCodef codef = new EasyCodef();
        codef.setClientInfoForDemo(CodefClientInfo.DEMO_CLIENT_ID, CodefClientInfo.DEMO_CLIENT_SECRET);
        codef.setClientInfo(CodefClientInfo.CLIENT_ID, CodefClientInfo.CLIENT_SECRET);
        codef.setPublicKey(CodefClientInfo.PUBLIC_KEY);

        String result;

        // 입력부 세팅
        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("organization", "0001");
        parameterMap.put("loginType", "5");
        parameterMap.put("loginUserName", licenseInputDto.getLoginUserName());
        parameterMap.put("identity", licenseInputDto.getIdentity());
        parameterMap.put("loginTypeLevel","1");
        parameterMap.put("phoneNo", licenseInputDto.getPhoneNo());
        parameterMap.put("birthDate", licenseInputDto.getBirthDate());
        parameterMap.put("licenseNo01", licenseInputDto.getLicenseNo01());
        parameterMap.put("licenseNo02", licenseInputDto.getLicenseNo02());
        parameterMap.put("licenseNo03", licenseInputDto.getLicenseNo03());
        parameterMap.put("licenseNo04", licenseInputDto.getLicenseNo04());
        parameterMap.put("serialNo", licenseInputDto.getSerialNo());
        parameterMap.put("userName", licenseInputDto.getLoginUserName());


        //API 요청
        try {
            result = codef.requestProduct(productUrl, EasyCodefServiceType.DEMO, parameterMap);
        } catch (UnsupportedEncodingException | JsonProcessingException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
        //결과값을 Hashmap으로 형변환
        HashMap<String, Object> responseMap = null;
        try {
            responseMap = new ObjectMapper().readValue(result, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //결과값에서 추가 인증에 필요한 데이터 추출 및 저장하여 리턴
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

    /** 면허증 API 추가 인증 메서드 **/
    public String TwoWay_License(HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
        //쉬운 코드에프 객체 생성 및 클라이언트 정보 설정
        String productUrl = "/v1/kr/public/ef/driver-license/status";
        EasyCodef codef = new EasyCodef();
        codef.setClientInfoForDemo(CodefClientInfo.DEMO_CLIENT_ID, CodefClientInfo.DEMO_CLIENT_SECRET);
        codef.setClientInfo(CodefClientInfo.CLIENT_ID, CodefClientInfo.CLIENT_SECRET);
        codef.setPublicKey(CodefClientInfo.PUBLIC_KEY);

        String result;

        //추가인증 요청 시에는 이지코드에프.requestCertification 으로 호출
        result = codef.requestCertification(productUrl, EasyCodefServiceType.DEMO, parameterMap);

        //결과값 확인
        System.out.println("추가인증(result) : " + result);

        HashMap<String, Object> TwoWayResponseMap = null;
        try {
            TwoWayResponseMap = new ObjectMapper().readValue(result, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HashMap<String, Object> resultMap = (HashMap<String, Object>)TwoWayResponseMap.get("result");
        String code = (String)resultMap.get("code");
        if(code.equals("CF-00000")){
            //결과값에서 추가 인증에 필요한 데이터 추출 및 저장하여 리턴
            HashMap<String, Object> dataMap = (HashMap<String, Object>)TwoWayResponseMap.get("data");

            String resAuthenticity = dataMap.get("resAuthenticity").toString();

            return resAuthenticity;
        }
        
        // 인증을 완료하지 않고 확인 버튼을 누를 시
        else if(code.equals("CF-03002")) {
            return "3";
        }
        else return "0";
    }

    /**차량등록원부 조회를 위한 정보를 가져오는 메서드**/
    public HashMap<String, Object> car_registration(CarInputDto carInputDto){
        //codef 객체 생성 및 key값, endpoint URL 설정
        String productUrl = "/v1/kr/public/mw/car-registration-a/issuance";
        EasyCodef codef = new EasyCodef();
        codef.setClientInfoForDemo(CodefClientInfo.DEMO_CLIENT_ID, CodefClientInfo.DEMO_CLIENT_SECRET);
        codef.setClientInfo(CodefClientInfo.CLIENT_ID, CodefClientInfo.CLIENT_SECRET);
        codef.setPublicKey(CodefClientInfo.PUBLIC_KEY);

        String result;

        // 입력부 세팅
        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("organization", "0001");
        parameterMap.put("loginType", "6");
        parameterMap.put("userName", carInputDto.getUserName());
        parameterMap.put("identity", carInputDto.getIdentity());
        parameterMap.put("loginTypeLevel","1");
        parameterMap.put("phoneNo", carInputDto.getPhoneNo());
        parameterMap.put("carNo", carInputDto.getCarNo());
        parameterMap.put("ownerName", carInputDto.getUserName());
        parameterMap.put("displyed", "1");
        parameterMap.put("isIdentityViewYn", "1");

        //API 요청
        try {
            result = codef.requestProduct(productUrl, EasyCodefServiceType.DEMO, parameterMap);
        } catch (UnsupportedEncodingException | JsonProcessingException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
        //결과값을 Hashmap으로 형변환
        HashMap<String, Object> responseMap = null;
        try {
            responseMap = new ObjectMapper().readValue(result, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //결과값에서 추가 인증에 필요한 데이터 추출 및 저장하여 리턴
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

    /** 자동차등록원부(갑) API 추가 인증 메서드 **/
    public HashMap<String, Object> TwoWay_Regstration(HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
        //쉬운 코드에프 객체 생성 및 클라이언트 정보 설정
        String productUrl = "/v1/kr/public/mw/car-registration-a/issuance";
        EasyCodef codef = new EasyCodef();
        codef.setClientInfoForDemo(CodefClientInfo.DEMO_CLIENT_ID, CodefClientInfo.DEMO_CLIENT_SECRET);
        codef.setClientInfo(CodefClientInfo.CLIENT_ID, CodefClientInfo.CLIENT_SECRET);
        codef.setPublicKey(CodefClientInfo.PUBLIC_KEY);

        String result;

        //추가인증 요청 시에는 이지코드에프.requestCertification 으로 호출
        result = codef.requestCertification(productUrl, EasyCodefServiceType.DEMO, parameterMap);

        //결과값 확인
        System.out.println("추가인증(result) : " + result);

        HashMap<String, Object> TwoWayResponseMap = null;
        try {
            TwoWayResponseMap = new ObjectMapper().readValue(result, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return TwoWayResponseMap;
    }
}
