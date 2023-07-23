package com.example.Blogging.Plateform2.repository;

import com.example.Blogging.Plateform2.model.AppUser;
import com.example.Blogging.Plateform2.model.constant.AppUserRole;
import com.example.Blogging.Plateform2.model.constant.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findByEmail(String email);


    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

/*    @Query(value="SELECT u FROM AppUser u WHERE u.appUserRole=?1")
    List<AppUser> findAllByRole(AppUserRole role);*/
/*

    @Query(

            "SELECT a " +
                    "FROM AppUser a " +
                    "WHERE  a.status =?1 and a.appUserRole =?2"

    )
    List<AppUser> findAllDriverStatus(UserStatus status,AppUserRole appUserRole);

    @Query(

            "SELECT a " +
                    "FROM AppUser a " +
                    "WHERE  a.status =?1 and a.appUserRole =?2 and a.area=?3"

    )
    List<AppUser> findAllDriverByLocation(UserStatus status, AppUserRole appUserRole, String location);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser c " + "SET c.status= ?2 " + "WHERE c.id = ?1")
    int updateStatus(Long id, UserStatus status);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser c " + "SET c.password= ?2 " + "WHERE c.email = ?1")
    int updatePassword(String email,String password);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser c " + "SET c.status= ?2 " + "WHERE c.email = ?1")
    int updateEmail(String email, UserStatus status);
*/



}
