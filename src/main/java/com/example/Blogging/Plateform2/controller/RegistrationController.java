package com.example.Blogging.Plateform2.controller;

import com.example.Blogging.Plateform2.constant.RequestRouting;
import com.example.Blogging.Plateform2.model.form.RegistrationRequest;
import com.example.Blogging.Plateform2.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@AllArgsConstructor
public class RegistrationController extends BaseController implements WebMvcConfigurer {

    private final RegistrationService registrationService;

    @PostMapping(RequestRouting.Registration.ROUTE)
    public String regis(@Valid @ModelAttribute("request") RegistrationRequest request, BindingResult result, Model model ){
        model.addAttribute("request", request);


        if (result.hasErrors()) {
            return "registration";
        }

        if(registrationService.emailAddressExists(request.getEmail())==false) {
            result.addError(new ObjectError("emailAddress", "This Email Address "
                    + request.getEmail() + " already exist"));
            return "registration";
        }

        return registrationService.register(request);
    }


    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {

        return registrationService.confirmToken(token);
    }
}
