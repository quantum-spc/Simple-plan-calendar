package shop.qtmspc.repository;

import shop.qtmspc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByMemberid(String id);
}
