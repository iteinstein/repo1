package com.fh.controller;

import com.fh.model.ServerResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("member")
public class MemberController {

    @RequestMapping(value="login")
    public ServerResponse login(String username, String password, HttpSession session){
        System.out.println(session.getId());
        Map<String,Object> member = new HashMap<>();
        member.put("username",username);
        member.put("phoneNumber","18101381273");
        member.put("email","iteinstein@163.com");
        session.setAttribute("member",member);
        return ServerResponse.success();
    }

    @RequestMapping(value="getCurrentMemberUsername")
    public ServerResponse getCurrentMemberUsername(HttpSession session){
        System.out.println(session.getId());
        Map<String,Object> member = (Map<String, Object>) session.getAttribute("member");
        return ServerResponse.success(member.get("username"));
    }

    @RequestMapping(value="getCurrentMember")
    public ServerResponse getCurrentMember(HttpSession session){
        System.out.println(session.getId());
        return ServerResponse.success(session.getAttribute("member"));
    }

}
