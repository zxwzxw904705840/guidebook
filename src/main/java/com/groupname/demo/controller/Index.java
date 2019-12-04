package com.groupname.demo.controller;

import com.groupname.demo.service.UpdateUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {
    @Autowired
    UpdateUserInformationService updateUserInformationService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
