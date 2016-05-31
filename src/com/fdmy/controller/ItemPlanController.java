package com.fdmy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmy.model.ItemPlan;
import com.fdmy.model.User;
import com.fdmy.service.IItemPlanService;

@Controller("itemPlanController")
@RequestMapping("/itemplan")
public class ItemPlanController {
	private IItemPlanService itemPlanService;

	@Resource
	public void setItemPlanService(IItemPlanService itemPlanService) {
		this.itemPlanService = itemPlanService;
	}

	public ItemPlanController() {
		System.out.println("a new ItemPlanController");
	}

	@RequestMapping("/index")
	public String index() {
		return "/itemplan/itemplanindex";
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(ItemPlan itemPlanBean, Model model) throws Exception {
		// 利用搜索对象查询结果
		List<ItemPlan> list = new ArrayList<ItemPlan>();
		list = itemPlanService.query(itemPlanBean);

//		model.addAttribute("itemQueryBean", itemQueryBean);
		model.addAttribute("itemplanlist", list);
		return "/itemplan/itemplanindex";
	}
	
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String load(String  id, Model model) throws Exception {
		// 根据id查询指定对象
		List<ItemPlan> list = new ArrayList<ItemPlan>();
		ItemPlan plan = itemPlanService.load(id);
		list.add(plan);
//		model.addAttribute("itemQueryBean", itemQueryBean);
		model.addAttribute("itemplanlist", list);
		return "/itemplan/itemplanindex";
	}

	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request,Model model) {
		ItemPlan itemPlan = new ItemPlan();
		//设置计划月份为当前月
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		itemPlan.setPlanMonth(sdf.format(Calendar.getInstance().getTime()));	
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginuser");
		//设置归属部门
		itemPlan.setDepartment(user.getDepartment());
		model.addAttribute("itemPlan", itemPlan);
		return "/itemplan/itemplanpage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid ItemPlan itemplan,BindingResult br,Model model) throws Exception {
		if(br.hasErrors()){
			List<ObjectError> errorList = br.getAllErrors();
			for (ObjectError error : errorList) {
				System.out.println(error.getDefaultMessage());
			}
			return "/itemplan/itemplanpage";
		}
		itemPlanService.add(itemplan);
		return "redirect:/itemplan/query?itemCode=" + itemplan.getItemCode() + "&planMonth=" + itemplan.getPlanMonth();
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String update(@PathVariable String id, Model model) throws Exception {
		ItemPlan plan = itemPlanService.load(id);
		model.addAttribute("itemPlan", plan);
		return "/itemplan/itemplanpage";
	}

	//RequestMapping的value中指定的id参数，可以当作表单中传递的值对待，此处自动赋值到plan对象中的id字段
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(@PathVariable String id,ItemPlan plan) throws Exception {
		itemPlanService.update(plan);
		return "redirect:/itemplan/load?id=" + plan.getId();
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String id) throws Exception {
		itemPlanService.delete(id);
		return "redirect:/itemplan/query?id=" + id;
	}

}
