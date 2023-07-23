package com.example.Blogging.Plateform2.service;

import com.example.Blogging.Plateform2.model.AppUser;
import com.example.Blogging.Plateform2.model.Blogger;
import com.example.Blogging.Plateform2.model.ConfirmationToken;
import com.example.Blogging.Plateform2.model.constant.UserStatus;
import com.example.Blogging.Plateform2.repository.AppUserRepository;
import com.example.Blogging.Plateform2.repository.BloggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND="USER WITH EMAIL %S NOT FOUND";

    private final AppUserRepository appUserRepository;


    private final BloggerRepository bloggerRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return appUserRepository.findByEmail(username)
                    .orElseThrow(()->
                            new UsernameNotFoundException(
                                    String.format(USER_NOT_FOUND,username)));
    }

    public Optional<AppUser> getAppUser(Long id) {
        return appUserRepository.findById(id);
    }

    public boolean emailAddressExists(String email) {
        boolean userExists=appUserRepository.findByEmail(email).isEmpty();

        return  userExists;
    }


    public String signUpUser(AppUser appUser){

        String encodedPassword=bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

            Blogger blogger=new Blogger(
                    false
            );
            appUser.setEnabled(true);
            appUser.setStatus(UserStatus.PENDING);
        bloggerRepository.save(blogger);
        appUser.setBlogger(blogger);
            appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();


        //Sending a confirmation token

        ConfirmationToken confirmationToken=new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser


        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);


        //Confirmation token=new C
        //TODO: SEND EMAIL
        return token;

    }


    public void signUpAmin(AppUser appUser){
        boolean userExists=appUserRepository.
                findByEmail(appUser.getEmail())
                .isPresent();


        if(userExists){
            throw new IllegalStateException("email already exist ");
        }

        String encodedPassword=bCryptPasswordEncoder.encode(appUser.getPassword());


        appUser.setPassword(encodedPassword);


        appUser.setEnabled(true);
        appUserRepository.save(appUser);




    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }




}
