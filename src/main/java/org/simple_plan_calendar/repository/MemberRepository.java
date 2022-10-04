package org.simple_plan_calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.simple_plan_calendar.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
