package com.zxtcw.zxtcwsystem.setting.security;

import com.zxtcw.starter.defaults.DefaultProviderServiceImpl;
import com.zxtcw.starter.mobile.MobileProviderServiceImpl;
import com.zxtcw.starter.username.UsernameProviderServiceImpl;
import com.zxtcw.starter.wechat.WeChatProviderServiceImpl;
import com.zxtcw.zxtcwsystem.system.entity.Role;
import com.zxtcw.zxtcwsystem.system.entity.User;
import com.zxtcw.zxtcwsystem.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SecurityProviderService implements DefaultProviderServiceImpl, MobileProviderServiceImpl, UsernameProviderServiceImpl, WeChatProviderServiceImpl {

    @Autowired
    private UserRepository userRepository;

    /**
     * @author Walter(翟笑天)
     * @date 2021/3/4
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.queryUserByLoginNameAndIsDeleteAndIsLocked(s,0,0);
        if(user == null){
            return null;
        }
        // 获取用户的角色
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 角色必须以`ROLE_`开头
        List<Role> listRoles = user.getRoles();
        for(Role r : listRoles) {
            authorities.add(new SimpleGrantedAuthority(r.getCode()));
        }
        return new org.springframework.security.core.userdetails.User(s,user.getPassword(),true,true,true,true,authorities);
    }
}
