package com.example.Blogging.Plateform2.repository;

import com.example.Blogging.Plateform2.model.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface BloggerRepository  extends JpaRepository<Blogger,Long> {

        Optional<Blogger> findById(Long id);


}


