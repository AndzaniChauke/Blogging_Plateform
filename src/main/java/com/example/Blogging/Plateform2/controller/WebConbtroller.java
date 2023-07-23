package com.example.Blogging.Plateform2.controller;

import com.example.Blogging.Plateform2.constant.Pages;
import com.example.Blogging.Plateform2.constant.RequestRouting;
import com.example.Blogging.Plateform2.model.form.RegistrationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebConbtroller {


    /**
     * Fetches Home Page
     * @return html
     */
    @GetMapping(RequestRouting.HOME)
    public String getIndexPage(){

        return Pages.HOME ;

    }

    /**
     * Fetches Login Page
     * @return html
     */
    @GetMapping(RequestRouting.Login.ROUTE)
    public String getLoginPage(){

        return Pages.LOGIN;

    }


    /**
     *
     * @param request form to be filled in
     * @param model {@link Model} spring mvc model
     * @return html
     */
    @GetMapping(RequestRouting.Registration.ROUTE)
    public String getRegistrationPage(RegistrationRequest request, Model model){

        model.addAttribute("request",request);
        return Pages.Registration.ROUTE;

    }

}
