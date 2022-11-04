package shop.qtmspc.repository;

import shop.qtmspc.entity.Calendar;
import shop.qtmspc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findAllByUserAndDelflag(User user, String delflag);
}
