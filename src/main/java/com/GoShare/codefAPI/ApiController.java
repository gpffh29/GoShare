package com.GoShare.codefAPI;

import com.GoShare.dto.MemberFormDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/member/License")
    public String LicenseForm(Model model) {
        model.addAttribute("ApiDto", new ApiDto());
        return "member/memberLicense";
    }

    @PostMapping("/member/License_form")
    public String LicenseForm_post(@ModelAttribute ApiDto apiDto, HttpSession session, Model model) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        HashMap<String, Object> resultmap=apiService.Driver_License(apiDto);
        session.setAttribute("resultmap", resultmap);
        model.addAttribute("ApiDto", apiDto);
        return "member/memberLicense";
    }


    @PostMapping("/member/License")
    public String License_result(@RequestParam("buttonValue") String simpleAuth, HttpSession session, Model model) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
        HashMap<String, Object> parameterMap = (HashMap<String, Object>) session.getAttribute("resultmap");
        parameterMap.put("simpleAuth", (String)simpleAuth);
        String resAuthenticity = apiService.TwoWay(parameterMap);
        if(resAuthenticity.equals("1")) {
            model.addAttribute("memberFormDto", new MemberFormDto());
            return "member/memberForm";
        }
        else {
            model.addAttribute("ApiDto", new ApiDto());
            model.addAttribute("failAuth", "면허 번호를 확인해주세요");
            return "member/memberLicense";
        }
    }
}
