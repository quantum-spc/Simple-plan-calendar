package shop.qtmspc.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.qtmspc.entity.User;

import java.util.List;

@Mapper
public interface BoardMapper {

    public List<User> findByMemberid(User user);

}
