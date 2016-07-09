package com.fdmy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmy.model.Item;
import com.fdmy.service.IItemService;
import com.github.pagehelper.PageInfo;

@Controller("itemController")
@RequestMapping("/item")
public class ItemController {
	private IItemService itemService;
	
	public IItemService getItemService() {
		return itemService;
	}

	@Resource
	public void setItemService(IItemService itemService) {
		this.itemService = itemService;
	}

	public ItemController() {
		System.out.println("a new ItemController");
	}

	@RequestMapping("/index")
	public String index() {
		return "/item/itemindex";
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(Item itemQueryBean, Model model) throws Exception {
		// 利用条件bean对象查询结果
		List<Item> itemList = new ArrayList<Item>();
		itemList = itemService.query(itemQueryBean);
		PageInfo<Item> page = new PageInfo<Item>(itemList);
//		model.addAttribute("itemQueryBean", itemQueryBean);
		model.addAttribute("itemList", itemList);
		model.addAttribute("pageInfo", page);
		return "/item/itemindex";
	}

	
	//录入 入库单、出库单时查询物料编码的方法
	@RequestMapping(value = "/queryforadd", method = RequestMethod.GET)
	public String query(Item itemQueryBean, Model model,String source) throws Exception {
		// 利用搜索对象查询结果
		List<Item> itemList = new ArrayList<Item>();
		itemList = itemService.query(itemQueryBean);
		PageInfo<Item> page = new PageInfo<Item>(itemList);
		model.addAttribute("source", source);
		model.addAttribute("itemList", itemList);
		model.addAttribute("pageInfo", page);
		return "/iteminpage";
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
		Item i = itemService.load(item.getCode());
		if (i != null) {
			br.addError(new FieldError("item","code","该物料编码已存在"));
//			model.addAttribute("item",item);
			return "/item/itempage";
		}
		itemService.add(item);
		return "redirect:/item/query?code=" + item.getCode();
	}

	@RequestMapping(value = "/{code}/update", method = RequestMethod.GET)
	public String update(@PathVariable String code, Model model) throws Exception {
		Item item = itemService.load(code);
		model.addAttribute("item", item);
		return "/item/itempage";
	}

	@RequestMapping(value = "/{code}/update", method = RequestMethod.POST)
	public String update(Item item) throws Exception {
		itemService.update(item);
		return "redirect:/item/query?code=" + item.getCode();
	}

	@RequestMapping(value = "/{code}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String code) throws Exception {
		itemService.delete(code);
		return "redirect:/item/query?code=" + code;
	}

}
