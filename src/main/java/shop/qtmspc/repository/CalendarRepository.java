package shop.qtmspc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import shop.qtmspc.entity.Calendar;
import shop.qtmspc.entity.User;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long>, QuerydslPredicateExecutor<Calendar> {

    List<Calendar> findAllByUserAndDelflag(User user, String delflag);
}
