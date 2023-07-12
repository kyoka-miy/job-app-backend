package com.example.securityjwtpracticespring.applications;

import com.example.securityjwtpracticespring.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByUser(User user);
    @Query("SELECT app FROM Application app WHERE app.user.userId = :userId")
    List<Application> findByUserId(Integer userId);
}
