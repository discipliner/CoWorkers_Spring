package com.crunch.controller;//package com.crunch.controller;

import com.crunch.domain.UserInfoDTO;
import com.crunch.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@Slf4j
public class UserInfoController {
    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value = "/")
    public String login(HttpServletRequest request, Model model) {
        log.info("login()");


        return "user/login";
    }

    @RequestMapping(value = "loginOK")
    public String loginOK(HttpServletRequest request, Model model) {
        log.info("loginOK()");
        String accountID = request.getParameter("accountID");
        log.info("accountID : " + accountID);
        String accountPassword = request.getParameter("accountPassword");
        log.info("accountPassword : " + accountPassword);
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        String originID = mapper.userInfoCompareID(accountID);
        log.info("originID : " + originID);
        String originPassword = mapper.userInfoComparePW(accountID);
        log.info("originPassword : " + originPassword);
        if (accountID !=null && accountID.equals(originID)) {
            log.info("accountID.equals(originID)");
            if (accountPassword !=null && accountPassword.equals(originPassword)) {
                log.info("accountPassword.equals(originPassword)");
                HttpSession session = request.getSession();
                log.info("session: " + session);
                UserInfoDTO userInfoDTO = new UserInfoDTO();
                log.info("user: " + userInfoDTO);
                userInfoDTO.setAccountID(accountID);
                log.info("user.setAccountID(accountID): " + userInfoDTO);
                ArrayList<UserInfoDTO> user = mapper.userInfoSelect(accountID);
                log.info("===========================: " + user);
                session.setAttribute("userInfo", user);
                return "user/mainView";
            } else {
                model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
                return "user/login";
            }
        } else {
            model.addAttribute("msg", "아이디가 틀렸습니다.");
            return "redirect:login";
        }

    }

    @RequestMapping(value = "logout")
    public String logout() {
        log.info("logout()");

        return "redirect:login";
    }
    @RequestMapping(value = "registerForm")
    public String registerForm() {
        log.info("registerForm()");

        return "user/registerForm";
    }
    @RequestMapping(value = "registerOK")
    public String registerOK(HttpServletRequest request, Model model, UserInfoDTO userInfoDTO) {
        log.info("registerOK()");
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        mapper.userInfoInsert(userInfoDTO);

        return "redirect:login";
    }

}
