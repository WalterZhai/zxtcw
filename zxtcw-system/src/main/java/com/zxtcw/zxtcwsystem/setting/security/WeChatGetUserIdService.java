package com.zxtcw.zxtcwsystem.setting.security;

import com.zxtcw.starter.wechat.WeChatGetUserIdImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Walter(翟笑天)
 * @Date 2021/3/4 8:58
 */
@Service
public class WeChatGetUserIdService implements WeChatGetUserIdImpl {

    /**
     * @comment 添加微信验证逻辑
     * 1.获取微信发送过来的code编码和标识appid的参数
     * 2.根据企业微信的 agentId_secret 和 appid 获得 token
     * 3.根据code和token 获得userId,既用户编码
     * @author Walter(翟笑天)
     * @date 2021/3/4
     */
    @Override
    public String getUserIdByToken(HttpServletRequest request, HttpServletResponse response) {
        String userid = "";
        //1.获取微信发送过来的code编码和标识appid的参数
        // String code = request.getParameter("code");
        // String state = request.getParameter("state");
        // String[] params = state.split(";");

        //2.根据企业微信的 agentId_secret 和 appid 获得 token
        // String token = wechatService.getToken(params[0]);

        //3.根据code和token 获得userId,既用户编码
        //String userid = wechatService.getUserInfoByUserid(code,token);

        return userid;
    }
}
