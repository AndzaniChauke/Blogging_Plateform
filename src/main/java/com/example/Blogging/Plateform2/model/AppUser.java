package com.example.Blogging.Plateform2.model;

import com.example.Blogging.Plateform2.model.constant.AppUserRole;
import com.example.Blogging.Plateform2.model.constant.Province;
import com.example.Blogging.Plateform2.model.constant.UserStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser  implements UserDetails {
    @Id
    @SequenceGenerator(
            name="random_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "random_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email ;
    private String password;
    @Enumerated(EnumType.STRING)
    private Province province;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked =false;
    private Boolean enabled=false;

    @Column(name = "STATUS", length = 15)
    @Enumerated(EnumType.STRING)
    private UserStatus status ;

    @ManyToOne
    @JoinColumn(

            name = "blogger_id"
    )
    private Blogger blogger ;


    public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName(){
        return firstName;
    }

    public  String getLastName(){
        return lastName;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Integer hasRole(String roleName) {
        AppUserRole UserLogin= AppUserRole.USER;
        AppUserRole AdminLogin= AppUserRole.ADMIN;
        AppUserRole ManagerLogin= AppUserRole.MEMBER;

        if(UserLogin.toString() == roleName){
            return 1;

        }else if(AdminLogin.toString()==roleName){
            return 2;
        }else if(ManagerLogin.toString()==roleName){
            return 3;
        }else{
            return 5;
        }

    }



}