package shop.qtmspc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.qtmspc.entity.User;

import java.util.List;

public interface UserRepositoryForNativeSql extends JpaRepository<User, Long> {

    @Query(
            value = " SELECT * FROM plan_user "+
                    " WHERE delflag = 'N' " +
                    " AND memberid = ? ",
            nativeQuery = true
    )
    List<User> findByMemberid(String memberid);
}
