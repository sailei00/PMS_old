package com.fdmy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmy.dao.IUserDao;
import com.fdmy.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	IUserDao userDao ;

	public IUserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public UserController() {
		System.out.println("a new UserController");
	}

	// 直接的页面跳转，没有任何逻辑
	@RequestMapping("/index")
	public String index() {
		return "/user/userindex";
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(User user, Model model) {
		List<User> list = userDao.query(user);
		model.addAttribute("userList", list);
		return "/user/userindex";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(Model model) {
		model.addAttribute("user", new User());
		return "/user/userpage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid User user, BindingResult br) throws Exception {
		if (br.hasErrors()) {
			List<ObjectError> errorList = br.getAllErrors();
			for (ObjectError error : errorList) {
				System.out.println(error.getDefaultMessage());
			}
			return "/user/userpage";
		}
		userDao.add(user);
		return "redirect:/user/query?usercode=" + user.getUsercode();
	}

	@RequestMapping(value = "/{usercode}/update", method = RequestMethod.GET)
	public String update(@PathVariable String usercode, Model model) throws Exception {
		User user = userDao.load(usercode);
		model.addAttribute("user", user);
		return "/user/userpage";
	}

	@RequestMapping(value = "/{usercode}/update", method = RequestMethod.POST)
	public String update(@Valid User user, BindingResult br) throws Exception {
		if (br.hasErrors()) {
			List<ObjectError> errorList = br.getAllErrors();
			for (ObjectError error : errorList) {
				System.out.println(error.getDefaultMessage());
			}
			return "/user/userpage";
		}
		userDao.update(user);
		return "redirect:/user/query?usercode=" + user.getUsercode();
	}
	

	@RequestMapping(value = "/{usercode}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String usercode) throws Exception {
		userDao.delete(usercode);
		return "redirect:/user/query";
	}

	@RequestMapping(value="/changepassword",method=RequestMethod.GET)
	public String changepassword(HttpServletRequest request ,Model model) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginuser");
		model.addAttribute("user",user);
		return "/user/changepassword";
	}
	
	@RequestMapping(value="/changepassword",method=RequestMethod.POST)
	public String changepassword(User user,BindingResult br,Model model) throws Exception {
		User u = userDao.load(user.getUsercode());
		if (!u.getPassword().equals(user.getOldpassword()) ) {
			br.addError(new FieldError("user", "oldpassword", "原密码错误!"));
			return "/user/changepassword";
		}
		userDao.changePassword(user);
		
		model.addAttribute("result","修改成功!");
		return "/user/changepassword";
	}

}
