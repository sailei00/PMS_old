package com.fdmy.controller;

import java.util.Calendar;
import java.util.List;

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

import com.fdmy.dao.DaoFactory;
import com.fdmy.dao.IAccountDao;
import com.fdmy.dao.IItemDao;
import com.fdmy.model.Account;
import com.fdmy.model.Item;
import com.fdmy.model.User;

@Controller
@RequestMapping("/account")
public class AccountController {
	private IAccountDao dao = DaoFactory.getAccountDao();
	private IItemDao itemDAO = DaoFactory.getItemDao();

	public AccountController() {
		System.out.println("a new AccountController");
	}

	@RequestMapping("/index")
	public String index() {
		return "/account/accountindex";
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(Account acc, Model model) {
		List<Account> list = dao.query(acc);
		model.addAttribute("accList", list);
		model.addAttribute("querybean", acc);
		return "/account/accountindex";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toInsert(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginuser");
		Account acc = new Account();
		if (user != null) {
			acc.setDepartment(user.getDepartment());
			acc.setOperator(user.getUsername());
			acc.setCreateTime(Calendar.getInstance().getTime());
			acc.setUpdateTime(Calendar.getInstance().getTime());
		}
		model.addAttribute("account", acc);
		return "/account/accountpage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid Account acc, BindingResult br) throws Exception {
		System.out.println("###########account##########add#####post");
		System.out.println(acc);
		System.out.println(acc.getItem());

		if (br.hasErrors()) {
			System.out.println("======================");
			List<ObjectError> errorList = br.getAllErrors();
			for (ObjectError error : errorList) {
				System.out.println(error.getDefaultMessage());
			}
			return "/account/accountpage";
		}

		Item item = itemDAO.load(acc.getItem().getCode());
		if (item == null) {
			br.addError(new FieldError("account", "item.code", "该物料编码不存在"));
			// br.addError(new ObjectError("item.code","该物料编码不存在"));
			return "/account/accountpage";
		}
		int type = acc.getType();
		if (type == 0) { // 0出库
			item.setAmount(item.getAmount() - acc.getNumber());
		} else if (type == 1) { // 1入库
			Double num = acc.getNumber();
			Double amount = item.getAmount();
			System.out.println(num + amount);
			item.setAmount(num + amount);
		}
		itemDAO.update(item);
		dao.add(acc);
		return "redirect:/account/query?item.code=" + acc.getItem().getCode();
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String update(@PathVariable String id, Model model) throws Exception {
		Account acc = dao.load(id);
		model.addAttribute("account", acc);
		return "/account/accountpage";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(@Valid Account acc, BindingResult br) throws Exception {
		if (br.hasErrors()) {
			List<ObjectError> errorList = br.getAllErrors();
			for (ObjectError error : errorList) {
				System.out.println(error.getDefaultMessage());
			}
			return "/account/accountpage";
		}
		dao.update(acc);
		return "redirect:/account/query?id=" + acc.getId();
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String id) throws Exception {
		dao.delete(id);
		return "redirect:/account/query?id=" + id;
	}

	@RequestMapping(value = "/checkstock", method = RequestMethod.GET)
	public String checkStock(Item item, String departmemt, Model model) {
		
		return null;
	}
	
	@RequestMapping(value = "/tocheckstock", method = RequestMethod.GET)
	public String checkStock() {
		
		return null;
	}

}
