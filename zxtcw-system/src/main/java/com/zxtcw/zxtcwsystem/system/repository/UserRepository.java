package com.zxtcw.zxtcwsystem.system.repository;

import com.zxtcw.zxtcwsystem.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @comment
 * @author Walter(翟笑天)
 * @date 2021/3/10
 */
@Transactional
public interface UserRepository extends JpaRepository<User,String> {

    User queryUserByLoginNameAndIsDeleteAndIsLocked(String loginName,Integer isDelete,Integer isLocked);

}
