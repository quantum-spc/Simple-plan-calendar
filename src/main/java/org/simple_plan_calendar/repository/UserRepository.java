package org.simple_plan_calendar.repository;

import org.simple_plan_calendar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByMemberid(String id);
}
