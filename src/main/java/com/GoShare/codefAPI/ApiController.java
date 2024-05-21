package com.GoShare.codefAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Controller
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/member/License")
    public String License_verification(Model model){
        model.addAttribute("ApiDto", new ApiDto());
        return "member/memberLicense";
    }

    @PostMapping("/member/License")
    public String License_post(ApiDto apiDto, HttpSession session){
        HashMap<String, Object> resultmap=apiService.Driver_License(apiDto);
        session.setAttribute("resultmap", resultmap);
        return "member/success";
    }


    @PostMapping("/member/License_result")
    public String License_result(@RequestParam("buttonValue") String simpleAuth, HttpSession session, Model model) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
        HashMap<String, Object> parameterMap = (HashMap<String, Object>) session.getAttribute("resultmap");
        System.out.println(parameterMap);
        parameterMap.put("simpleAuth", (String)simpleAuth);
        String result = apiService.TwoWay(parameterMap);
        System.out.println(parameterMap);
        model.addAttribute("result", result);
        return "member/success";
    }

    @GetMapping("/member/License")
    public String License_verification2(Model model){
        model.addAttribute("ApiDto", new ApiDto());
        return "popupexample";
    }


}
