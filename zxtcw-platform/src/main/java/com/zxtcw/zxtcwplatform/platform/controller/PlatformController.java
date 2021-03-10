package com.zxtcw.zxtcwplatform.platform.controller;

import com.zxtcw.zxtcwplatform.dao.platform.PlatformMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @comment
 * @author Walter(翟笑天)
 * @date 2021/3/6
 */
@RestController
public class PlatformController {

    static final Logger logger = LoggerFactory.getLogger(PlatformController.class);

    @Autowired
    private PlatformMapper platformMapper;

    @GetMapping(value="/platformTest")
    public String platformTest(HttpServletRequest request) {
        String name = platformMapper.getNameByUsername("00001");
        return name;
    }




}
