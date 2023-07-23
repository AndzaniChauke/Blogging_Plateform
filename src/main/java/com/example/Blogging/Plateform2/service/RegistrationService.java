package com.example.Blogging.Plateform2.service;

import com.example.Blogging.Plateform2.model.form.RegistrationRequest;

public interface RegistrationService {

    public boolean emailAddressExists(String email);

    public String register(RegistrationRequest request);

    public String confirmToken(String token);


}
