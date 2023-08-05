package com.example.Blogging.Plateform2.service;

import com.example.Blogging.Plateform2.model.AppUser;
import com.example.Blogging.Plateform2.model.constant.UserStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        AppUser appUser = (AppUser) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (appUser.isUser()) {
            if (appUser.getStatus() == UserStatus.PENDING || appUser.getStatus() == UserStatus.WAITING) {
                if (appUser.getBlogger().getSubmitted()) {
                    redirectURL = "submission_success";

                } else {
                    redirectURL = "driver/driver_submit";

                }
            } else {
                redirectURL = "user/waiting_submission";
            }
            redirectURL = "user_home";
        } else if (appUser.isAdmin()) {
            redirectURL = "employee";
        } else if(appUser.isMember()) {
            redirectURL = "member";
         }else {
            redirectURL = "404";

        }

        response.sendRedirect(redirectURL);


    }

}