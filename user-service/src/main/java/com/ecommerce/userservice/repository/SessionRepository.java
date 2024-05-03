package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.model.Session;
import com.ecommerce.userservice.model.SessionsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    Optional<Session>findByTokenAndUser_Id(String token,Long UserId);

    @Override
    List<Session> findAll();

    Optional<List<Session>>findByUserIdAndSessionStatus(Long user_id, SessionsStatus sessionStatus);


    Optional<Session> findByTokenAndSessionStatus(String token, SessionsStatus sessionsStatus);
}
