package com.fdmy.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmy.controller.vo.ReportVO;
import com.fdmy.model.User;
import com.fdmy.service.IReportService;

/*
 * 报表相关操作控制器
 */
@Controller("reportController")
@RequestMapping("/report")
public class ReportController {
	private IReportService reportService;

	public IReportService getReportService() {
		return reportService;
	}

	@Resource
	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public ReportController() {
		System.out.println("a new ReportController");
	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		ReportVO report = new ReportVO();
		User user = (User)session.getAttribute("loginuser");
		report.setDepartment(user.getDepartment());
		model.addAttribute("querybean", report);
		return "/report/reportindex";
	}

	@RequestMapping(value = "/getreport", method = RequestMethod.GET)
	public String getReport(ReportVO report, Model model) {
		if (report.getMonth() == null || report.getMonth().equals("")) {
			// 设置计划月份为当前月
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			report.setMonth(sdf.format(Calendar.getInstance().getTime()));
		}
		List<ReportVO> list = reportService.getReport(report);
		List<HashMap<String,String>> costList = reportService.getCost(report);
		System.out.println(costList.size());
//		for(HashMap<String,String> map:costList) {
//			System.out.println(map.get("price"));
//			System.out.println(map.get("costtype"));
//		}
		model.addAttribute("querybean", report);
		model.addAttribute("reportList", list);
		model.addAttribute("costList", costList);
		model.addAttribute("month", report.getMonth());
		model.addAttribute("department", report.getDepartment());
		return "/report/reportindex"; 
	}

}
