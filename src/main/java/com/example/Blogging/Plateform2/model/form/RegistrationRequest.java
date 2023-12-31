package com.example.Blogging.Plateform2.model.form;

import com.example.Blogging.Plateform2.model.constant.AppUserRole;
import com.example.Blogging.Plateform2.model.constant.Province;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class  RegistrationRequest {

    @NotEmpty(message = "The FirstName cannot be empty!")
    private  String firstName;

    @NotEmpty(message = "The LastName cannot be empty!")
    private  String lastName;

    @NotEmpty(message = "The Email Address cannot be empty!")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email address!")
    private String email;

    @NotEmpty(message = "The Password cannot be empty!")
    private String password;

    private String personalWebsite;

    private String link;

    private AppUserRole appUserRole;


}
