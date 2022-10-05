package org.simple_plan_calendar.repository;

import org.simple_plan_calendar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByMemberid(String id);
}
