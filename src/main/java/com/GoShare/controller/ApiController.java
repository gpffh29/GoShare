package com.GoShare.controller;

import com.GoShare.entity.Member;
import com.GoShare.service.ApiService;
import com.GoShare.dto.CarInputDto;
import com.GoShare.dto.CarOutputDto;
import com.GoShare.dto.LicenseInputDto;
import com.GoShare.dto.MemberFormDto;
import com.GoShare.service.CarService;
import com.GoShare.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

@RequiredArgsConstructor
@Controller
public class ApiController {

    private final ApiService apiService;
    private final CarService carService;
    private final MemberService memberService;

    /** 면허증 진위 여부 API 사용 컨트롤러 **/
    @GetMapping("/member/License")
    public String LicenseForm(Model model) {
        model.addAttribute("LicenseInputDto", new LicenseInputDto());
        return "member/memberLicense";
    }

    @PostMapping("/member/License_form")
    public String LicenseForm_post(@ModelAttribute LicenseInputDto licenseInputDto, HttpSession session, Model model) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        HashMap<String, Object> resultmap=apiService.Driver_License(licenseInputDto);
        session.setAttribute("resultmap", resultmap);
        model.addAttribute("LicenseInputDto", licenseInputDto);
        return "member/memberLicense";
    }


    @PostMapping("/member/License")
    public String License_result(@RequestParam("buttonValue") String simpleAuth, HttpSession session, Model model) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
        HashMap<String, Object> parameterMap = (HashMap<String, Object>) session.getAttribute("resultmap");
        parameterMap.put("simpleAuth", (String)simpleAuth);
        String resAuthenticity = apiService.TwoWay_License(parameterMap);
        if(resAuthenticity.equals("1")) {
            model.addAttribute("memberFormDto", new MemberFormDto());
            return "member/memberForm";
        }
        else if(resAuthenticity.equals("3")){
            model.addAttribute("LicenseInputDto", new LicenseInputDto());
            model.addAttribute("failAuth", "인증을 완료한 뒤 확인 버튼을 눌러주세요");
            return "member/memberLicense";
        } else if (resAuthenticity.equals("4")) {
            model.addAttribute("LicenseInputDto", new LicenseInputDto());
            model.addAttribute("failAuth", "성명과 주민등록번호 또는 전화번호를 확인해주세요");
            return "member/memberLicense";
        } else{
            model.addAttribute("LicenseInputDto", new LicenseInputDto());
            model.addAttribute("failAuth", "면허 번호와 일련번호를 확인해주세요");
            return "member/memberLicense";
        }
    }

    /** 자동차등록원부(갑) API 사용 컨트롤러 **/

    @GetMapping("/car/registration")
    public String registrationForm(Model model) {
        model.addAttribute("CarInputDto", new CarInputDto());
        return "car/carForm";
    }

    @PostMapping("/car/registration_form")
    public String registrationForm_post(@ModelAttribute CarInputDto carInputDto, HttpSession session, Model model){
        HashMap<String, Object> resultmap=apiService.car_registration(carInputDto);
        session.setAttribute("resultmap", resultmap);
        model.addAttribute("CarInputDto", carInputDto);
        return "car/carForm";
    }

    @PostMapping("/car/registration")
    public String registrationForm_result(@RequestParam("buttonValue") String simpleAuth, HttpSession session, Model model) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
        HashMap<String, Object> parameterMap = (HashMap<String, Object>) session.getAttribute("resultmap");
        parameterMap.put("simpleAuth", (String)simpleAuth);
        HashMap<String, Object> TwoWayResponseMap = apiService.TwoWay_Regstration(parameterMap);

        //응답 코드 값으로 필터링
        HashMap<String, Object> resultMap = (HashMap<String, Object>)TwoWayResponseMap.get("result");
        String code = (String)resultMap.get("code");
        if(code.equals("CF-00000")){
            // API 호출 성공 시 차량 정보를 테이블에 저장
            HashMap<String, Object> dataMap = (HashMap<String, Object>)TwoWayResponseMap.get("data");
            CarOutputDto carOutputDto = new CarOutputDto();
            carOutputDto.setCar_name(dataMap.get("commCarName").toString());
            carOutputDto.setCar_type(dataMap.get("resCarModelType").toString());
            carOutputDto.setCar_model(dataMap.get("resCarYearModel").toString());
            carOutputDto.setCar_number(dataMap.get("resCarNo").toString());

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String member_email = (String) authentication.getName();
            Member member = memberService.findMemberByEmail(member_email);

            carService.saveCar(carOutputDto, member);
            model.addAttribute("message", "차량 등록에 성공하였습니다");
            return "car/registrationSuccess";
        }
        else if(code.equals("CF-13230")){
            model.addAttribute("message", "입력된 차량번호와 소유자명이 일치하지 않습니다");
            return "car/registrationFail";
        }
        else if(code.equals("CF-13225")){
            model.addAttribute("message", "존재하지 않는 차량번호(차대번호)입니다");
            return "car/registrationFail";
        }
        else{
            model.addAttribute("message", "인증 완료 후 확인 버튼을 눌러주세요");
            return "car/registrationFail";
        }
    }
}
