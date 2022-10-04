package org.simple_plan_calendar.repository;

import org.simple_plan_calendar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 게시물로 댓글 목록 가져오기
    User findByMemberid(String id);
}
