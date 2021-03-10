package com.zxtcw.zxtcwsystem.system.repository;

import com.zxtcw.zxtcwsystem.system.entity.Permit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @comment
 * @author Walter(翟笑天)
 * @date 2021/3/10
 */
@Transactional
public interface PermitRepository extends JpaRepository<Permit,String> {


}
