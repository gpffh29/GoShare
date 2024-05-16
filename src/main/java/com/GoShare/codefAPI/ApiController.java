package com.GoShare.codefAPI;

import com.GoShare.codefAPI.ApiService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/business-status")
    public String showForm() {
        return "business-status";
    }

    @PostMapping("/business-status")
    public String getBusinessStatus(@RequestParam("reqIdentity") String reqIdentity, Model model) {
        try {
            JsonNode businessStatus = apiService.getBusinessStatus(reqIdentity);
            model.addAttribute("businessStatus", businessStatus);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "business-status";
    }
}
