package com.zxtcw.zxtcwsystem.setting.security;

import com.zxtcw.starter.session.SetSessionAttributeImpl;
import com.zxtcw.zxtcwsystem.system.entity.User;
import com.zxtcw.zxtcwsystem.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Walter(翟笑天)
 * @Date 2021/3/4 8:58
 */
@Service
public class SetSessionAttributeService implements SetSessionAttributeImpl {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void setArrtibute(HttpServletRequest request, Authentication authentication) throws ServletException {
        User user = userRepository.queryUserByLoginNameAndIsDeleteAndIsLocked(authentication.getName(),0,0);
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        session.setAttribute("username",authentication.getName());
    }
}
