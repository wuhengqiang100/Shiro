package com.xiaoshu.admin.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xiaoshu.admin.entity.vo.ShowMenuVo;
import com.xiaoshu.admin.service.MenuService;
import com.xiaoshu.admin.service.UserService;
import com.xiaoshu.common.annotation.SysLog;
import com.xiaoshu.common.config.MySysUser;
import com.xiaoshu.common.exception.UserTypeAccountException;
import com.xiaoshu.common.realm.AuthRealm;
import com.xiaoshu.common.util.Constants;
import com.xiaoshu.common.util.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Controller
public class PropertyController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);

    public final static String LOGIN_TYPE = "loginType";

    @Autowired
    @Qualifier("captchaProducer")
    DefaultKaptcha captchaProducer;


    @GetMapping(value = "")
    public String welcome() {
        return "redirect:property";
    }

    @GetMapping(value = {"property"})
    public String adminIndex(RedirectAttributes attributes, ModelMap map) {
        Subject s = SecurityUtils.getSubject();
        if (s.isAuthenticated()) {
            return "redirect:index";
        }
        return "redirect:toProperty";
    }


    @GetMapping(value = "toProperty")
    public String adminToLogin() {
            return "admin/property";
    }

    @GetMapping("admin/property")
    @SysLog("属性选择Get")
    @ResponseBody
    public ResponseEntity adminLoginGet(HttpServletRequest request) {

        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setSuccess(Boolean.TRUE);
        responseEntity.setAny("url", "admin/login");
        return responseEntity;

        /*ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setMessage("请登录");
        responseEntity.setAny("url","tologin");
        return responseEntity;*/
      /*  String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String code = request.getParameter("code");
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ResponseEntity.failure("用户名或者密码不能为空");
        }else if(StringUtils.isBlank(code)){
            return ResponseEntity.failure("验证码不能为空");
        }
        HttpSession session = request.getSession();
        if(session == null){
            return ResponseEntity.failure("session超时");
        }
        String trueCode = (String)session.getAttribute(Constants.VALIDATE_CODE);
        if(StringUtils.isBlank(trueCode)){
            return ResponseEntity.failure("验证码超时");
        }
        if(StringUtils.isBlank(code) || !trueCode.toLowerCase().equals(code.toLowerCase())){
            return ResponseEntity.failure("验证码错误");
        }else {
            String errorMsg = null;
            Subject user = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,password,Boolean.valueOf(rememberMe));
            try {
                user.login(token);
                LOGGER.debug(username+"用户"+LocalDate.now().toString()+":======》登陆系统!");
            }catch (IncorrectCredentialsException e) {
                errorMsg = "用户名密码错误!";
            }catch (UnknownAccountException e) {
                errorMsg = "账户不存在!";
            }catch (LockedAccountException e) {
                errorMsg = "账户已被锁定!";
            }catch (UserTypeAccountException e) {
                errorMsg = "账户不是管理用户!";
            }

            if(StringUtils.isBlank(errorMsg)) {
                ResponseEntity responseEntity = new ResponseEntity();
                responseEntity.setSuccess(Boolean.TRUE);
                responseEntity.setAny("url","index");
                return responseEntity;
            }else {
                return ResponseEntity.failure(errorMsg);
            }
        }*/
    }

    @PostMapping("admin/property")
    @SysLog("属性选择Post")
    @ResponseBody
    public ResponseEntity adminLogin(HttpSession session,HttpServletRequest request) {
        HashMap pro=new HashMap();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tell = request.getParameter("tel");
        String email = request.getParameter("email");
        String identity = request.getParameter("identity");
        String sex = request.getParameter("sex");
        String fastLogin=request.getParameter("fastLogin");

        ResponseEntity res = new ResponseEntity();
        if (StringUtils.equals("on",fastLogin)){
            res.setSuccess(Boolean.TRUE);
            res.setAny("url", "admin/fastLogin");
            return res;
        }
        if (StringUtils.equals("on", username)) {
            pro.put("username", 1);
        }
        if (StringUtils.equals("on", password)) {
            pro.put("password", 1);
        }
        if (StringUtils.equals("on", tell)) {
            pro.put("tell", 1);
        }
        if (StringUtils.equals("on", email)) {
            pro.put("email", 1);
        }
        if (StringUtils.equals("on", identity)) {
            pro.put("identity", 1);
        }
        if (StringUtils.equals("on", sex)) {
            pro.put("sex", 1);
        }
        if(pro.size()<3){
            res.setSuccess(Boolean.FALSE);
            return  ResponseEntity.failure("选择认证的属性必须三个及以上!");
        }else{
            res.setSuccess(Boolean.TRUE);
            session.setAttribute("pro",pro);
            res.setAny("url", "admin");
            return res;
        }
    }
/*    @GetMapping(value = "toRegister")
    public String adminToRegister(HttpSession session, @ModelAttribute(LOGIN_TYPE) String loginType) {
        if(StringUtils.isBlank(loginType)) {
            LoginTypeEnum attribute = (LoginTypeEnum) session.getAttribute(LOGIN_TYPE);
            loginType = attribute == null ? loginType : attribute.name();
        }

        if(LoginTypeEnum.ADMIN.name().equals(loginType)) {
            return "admin/property";
        }else {
            return "login";
        }
    }*/

   /* @GetMapping(value = "index")
    public String index(HttpSession session, @ModelAttribute(LOGIN_TYPE) String loginType) {
        if(StringUtils.isBlank(loginType)) {
            LoginTypeEnum attribute = (LoginTypeEnum) session.getAttribute(LOGIN_TYPE);
            loginType = attribute == null ? loginType : attribute.name();
        }
        if(LoginTypeEnum.ADMIN.name().equals(loginType)) {
            AuthRealm.ShiroUser principal = (AuthRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
            session.setAttribute("icon",StringUtils.isBlank(principal.getIcon()) ? "/static/admin/img/face.jpg" : principal.getIcon());
            return "admin/property";
        }else {
            return "index";
        }

    }
*/
    /*@GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = captchaProducer.createText();
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
        LOGGER.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = captchaProducer.createImage(verifyCode);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }*/




    /*@GetMapping("admin/main")
    public String main(ModelMap map){
        return "admin/main";
    }

    *//***
     * 获得用户所拥有的菜单列表
     * @return
     *//*
    @GetMapping("/admin/user/getUserMenu")
    @ResponseBody
    public List<ShowMenuVo> getUserMenu(){
        String userId = MySysUser.id();
        List<ShowMenuVo> list = menuService.getShowMenuByUser(userId);
        return list;
    }

    @GetMapping("systemLogout")
    @SysLog("退出系统")
    public String logOut(){
        SecurityUtils.getSubject().logout();
        return "redirect:admin";
    }*/

}
