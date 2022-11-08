package shop.qtmspc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.qtmspc.entity.User;

import java.util.List;

public interface UserRepositoryForJpql extends JpaRepository<User, Long> {

    @Query("select b from User b where b.delflag = 'N' and  b.memberid =:memberid")
    List<User> findByMemberid(@Param("memberid") String memberid);
}
