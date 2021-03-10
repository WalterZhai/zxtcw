package com.zxtcw.zxtcwsystem.setting.init;

import com.zxtcw.starter.constant.Constant;
import com.zxtcw.zxtcwsystem.system.entity.Role;
import com.zxtcw.zxtcwsystem.system.entity.User;
import com.zxtcw.zxtcwsystem.system.repository.RoleRepository;
import com.zxtcw.zxtcwsystem.system.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Walter(翟笑天)
 * @Date 2021/3/10
 */
@Service
@Transactional
public class InitImplementsService {

    static final Logger logger = LoggerFactory.getLogger(InitImplementsService.class);

    @PersistenceContext(unitName = "PersistenceUnitUnimax")
    private EntityManager unimaxEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * @comment 初始化spring session 表
     * @author Walter(翟笑天)
     * @date 2021/3/10
     */
    public void initSpringSessionTable(){
        String judgeSql = " select COUNT(*) from information_schema.tables where table_name ='spring_session' ";
        BigInteger judge = (BigInteger) unimaxEntityManager.createNativeQuery(judgeSql).getSingleResult();
        if(judge.compareTo(BigInteger.ZERO)==0){
            StringBuffer sql = new StringBuffer();
            sql.append(" CREATE TABLE `spring_session` ( ");
            sql.append("   `PRIMARY_ID` char(36) NOT NULL, ");
            sql.append("   `SESSION_ID` char(36) NOT NULL, ");
            sql.append("   `CREATION_TIME` bigint(20) NOT NULL, ");
            sql.append("   `LAST_ACCESS_TIME` bigint(20) NOT NULL, ");
            sql.append("   `MAX_INACTIVE_INTERVAL` int(11) NOT NULL, ");
            sql.append("   `EXPIRY_TIME` bigint(20) NOT NULL, ");
            sql.append("   `PRINCIPAL_NAME` varchar(100) DEFAULT NULL, ");
            sql.append("   PRIMARY KEY (`PRIMARY_ID`), ");
            sql.append("   UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`), ");
            sql.append("   KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`), ");
            sql.append("   KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`) ");
            sql.append(" ) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC ");
            unimaxEntityManager.createNativeQuery(sql.toString()).executeUpdate();
            logger.info("spring session 主表建立成功");
        }
        judgeSql = " select COUNT(*) from information_schema.tables where table_name ='spring_session_attributes' ";
        judge = (BigInteger) unimaxEntityManager.createNativeQuery(judgeSql).getSingleResult();
        if(judge.compareTo(BigInteger.ZERO)==0){
            StringBuffer sql = new StringBuffer();
            sql.append(" CREATE TABLE `spring_session_attributes` ( ");
            sql.append("   `SESSION_PRIMARY_ID` char(36) NOT NULL, ");
            sql.append("   `ATTRIBUTE_NAME` varchar(200) NOT NULL, ");
            sql.append("   `ATTRIBUTE_BYTES` blob NOT NULL, ");
            sql.append("   PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`), ");
            sql.append("   CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE ");
            sql.append(" ) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC ");
            unimaxEntityManager.createNativeQuery(sql.toString()).executeUpdate();
            logger.info("spring session 副表建立成功");
        }
    }

    /**
     * @comment 初始化admin用户 和 系统管理员角色
     * @author Walter(翟笑天)
     * @date 2021/3/10
     */
    public void initAdminUserAndAdminRole(){
        User user = userRepository.queryUserByLoginNameAndIsDeleteAndIsLocked(Constant.SYSTEM_ADMIN,0,0);
        if(user==null){
            user = new User();
            user.setLoginName(Constant.SYSTEM_ADMIN);
            user.setPassword(Constant.PASSWORD);
            user.setName(Constant.SYSTEM_ADMIN);
            user.setIsLocked(0);
            user.setCreateId(Constant.SYSTEM_ADMIN);
            user.setModifyId(Constant.SYSTEM_ADMIN);
            Role role = roleRepository.queryRoleByCodeAndIsDelete(Constant.SYSTEM_ROLE,0);
            if(role==null){
                role = new Role();
                role.setCode(Constant.SYSTEM_ROLE);
                role.setName(Constant.SYSTEM_ROLE_NAME);
                role.setDescription(Constant.SYSTEM_ROLE_DESCRIPTION);
                role.setCreateId(Constant.SYSTEM_ADMIN);
                role.setModifyId(Constant.SYSTEM_ADMIN);
            }
            user.getRoles().add(role);
            userRepository.save(user);
            logger.info("初始化admin和系统管理员角色完成！");
        }

    }

}
