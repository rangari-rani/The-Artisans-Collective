package com.art.controller;

import com.art.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping
    public ApiResponse HomeControllerHandler(){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Welcome");
        return apiResponse;
    }
}
