package com.GoShare.codefAPI;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/member/License")
    public String License_verification(Model model){
        model.addAttribute("ApiDto", new ApiDto());
        return "memberLicense";
    }

    @PostMapping("/member/License")
    public String License_post(@Valid ApiDto apiDto, BindingResult bindingResult, Model model) throws IOException, InterruptedException {
        if(bindingResult.hasErrors()){
            return "memberLicense";
        }
        String businessNumber = apiDto.getBusinessNumber();
        String result=apiService.getBusinessStatus(businessNumber);
        model.addAttribute("result", result);
        return "success";
    }
}
