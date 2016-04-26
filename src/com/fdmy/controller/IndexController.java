package com.fdmy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmy.dao.IUserDao;
import com.fdmy.exception.UserException;
import com.fdmy.model.User;

@Controller
@SessionAttributes("loginuser")
@RequestMapping("/")
public class IndexController {
	private IUserDao userDao;

	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(){
		return "/index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "/login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String usercode, String password, Model model) {
		User user = userDao.load(usercode);
		if (null == user) {
			throw new UserException("用户名不存在!");
		}
		if (user.getStatus() == 9) {
			throw new UserException("账号状态异常，请联系管理员!");
		}
		if (user.getStatus() == 0) {
			throw new UserException("您的账号已被停用，请联系管理员！");
		}
		if (!password.equals(user.getPassword())) {
			throw new UserException("密码错误!");
		}
		model.addAttribute("loginuser", user);
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) {
		model.asMap().remove("loginuser");
		session.invalidate();
		return "redirect:/login";
	}

}
