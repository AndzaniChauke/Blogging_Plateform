package com.example.Blogging.Plateform2.controller;

import com.example.Blogging.Plateform2.constant.RequestRouting;
import com.example.Blogging.Plateform2.model.form.RegistrationRequest;
import com.example.Blogging.Plateform2.service.RegistrationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class RegistrationControllerUniTest {


    @Mock
    private RegistrationService registrationService;

    @InjectMocks
    private static RegistrationController registrationController;

    protected static MockMvc mockMvc;

    @BeforeAll
    static void beforeAll() {

    }

    @Test
    public void testRegis_withValidRequestAndUniqueEmail() {

        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("test@example.com");


        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Model model = Mockito.mock(Model.class);


        when(registrationService.emailAddressExists(request.getEmail())).thenReturn(false);

        String result = registrationController.regis(request, bindingResult, model);


        Mockito.verify(registrationService).register(request);

        assertEquals("expected_view_name", result); // Replace "expected_view_name" with the expected view name
    }

    @Test
    public void testRegis_withInvalidRequest() {
        // Create an invalid registration request
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail(""); // Invalid email address

        // Create a mock BindingResult and Model
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Model model = Mockito.mock(Model.class);

        // Set the hasErrors flag to true
        when(bindingResult.hasErrors()).thenReturn(true);

        // Call the regis method
        String result = registrationController.regis(request, bindingResult, model);

        // Verify that the registration service was not called
        Mockito.verify(registrationService, Mockito.never()).register(request);

        // Verify that the returned view name is correct
        assertEquals("expected_view_name", result); // Replace "expected_view_name" with the expected view name
    }

    @Test
    public void testRegis_withExistingEmail() {
        // Create a registration request with an existing email address
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("existing@example.com");

        // Create a mock BindingResult and Model
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Model model = Mockito.mock(Model.class);

        // Mock the email address check
        when(registrationService.emailAddressExists(request.getEmail())).thenReturn(true);

        // Call the regis method
        String result = registrationController.regis(request, bindingResult, model);

        // Verify that the registration service was not called
        Mockito.verify(registrationService, Mockito.never()).register(request);

        // Verify that the expected error was added to the BindingResult
        Mockito.verify(bindingResult).addError(Mockito.any(ObjectError.class));

        // Verify that the returned view name is correct
        assertEquals("expected_view_name", result); // Replace "expected_view_name" with the expected view name
    }

    @Test
    public void testRdegis_withExistingEmail() throws Exception {
        ModelAndView success=
                (ModelAndView) this.mockMvc
                        .perform(post(RequestRouting.HOME));


                assertNotNull(success);



    }


}

