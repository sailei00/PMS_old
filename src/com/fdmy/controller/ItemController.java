package com.fdmy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmy.dao.DAOFactory;
import com.fdmy.dao.IItemDao;
import com.fdmy.model.Item;

@Controller
@RequestMapping("/item")
public class ItemController {
	private IItemDao dao = DAOFactory.getItemDao();

	public ItemController() {
		System.out.println("a new ItemController");
	}

	@RequestMapping("/index")
	public String index() {
		return "/item/itemquery";
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(Item itemQueryBean, Model model) throws Exception {
		// 利用搜索对象查询结果
		List<Item> itemList = new ArrayList<Item>();
		itemList = dao.query(itemQueryBean);

//		model.addAttribute("itemQueryBean", itemQueryBean);
		model.addAttribute("itemList", itemList);
		return "/item/itemquery";
	}

	
	//录入 入库单、出库单时查询物料编码的方法
	@RequestMapping(value = "/queryforadd", method = RequestMethod.GET)
	public String query(Item itemQueryBean, Model model,String abc) throws Exception {
		// 利用搜索对象查询结果
		List<Item> itemList = new ArrayList<Item>();
		itemList = dao.query(itemQueryBean);
		System.out.println(abc+"★★★★★★★★★★★★★★★★★★★★★★★★★★");
//		model.addAttribute("itemQueryBean", itemQueryBean);
		model.addAttribute("itemList", itemList);
		return "/account/search";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(Model model) {
		model.addAttribute("item", new Item());
		return "/item/itempage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid Item item,BindingResult br,Model model) throws Exception {
		if(br.hasErrors()){
			List<ObjectError> errorList = br.getAllErrors();
			for (ObjectError error : errorList) {
				System.out.println(error.getDefaultMessage());
			}
			return "/item/itempage";
		}
		Item i = dao.load(item.getCode());
		if (i != null) {
			br.addError(new FieldError("item","code","该物料编码已存在"));
//			model.addAttribute("item",item);
			return "/item/itempage";
		}
		dao.add(item);
		return "redirect:/item/query?code=" + item.getCode();
	}

	@RequestMapping(value = "/{code}/update", method = RequestMethod.GET)
	public String update(@PathVariable String code, Model model) throws Exception {
		Item item = dao.load(code);
		model.addAttribute("item", item);
		return "/item/itempage";
	}

	@RequestMapping(value = "/{code}/update", method = RequestMethod.POST)
	public String update(Item item) throws Exception {
		dao.update(item);
		return "redirect:/item/query?code=" + item.getCode();
	}

	@RequestMapping(value = "/{code}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String code) throws Exception {
		dao.delete(code);
		return "redirect:/item/query?code=" + code;
	}

}
