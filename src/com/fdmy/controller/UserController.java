package com.fdmy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmy.dao.DAOFactory;
import com.fdmy.dao.IUserDao;
import com.fdmy.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	IUserDao dao = DAOFactory.getUserDao();

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
		List<User> list = dao.query(user);
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
		dao.add(user);
		return "redirect:/user/query?usercode=" + user.getUsercode();
	}

	@RequestMapping(value = "/{usercode}/update", method = RequestMethod.GET)
	public String update(@PathVariable String usercode, Model model) throws Exception {
		User user = dao.load(usercode);
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
		dao.update(user);
		return "redirect:/user/query?usercode=" + user.getUsercode();
	}
	

	@RequestMapping(value = "/{usercode}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String usercode) throws Exception {
		dao.delete(usercode);
		return "redirect:/user/query";
	}
	
}
