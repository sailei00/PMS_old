package com.fdmy.dao;

import java.util.HashMap;
import java.util.List;

import com.fdmy.controller.vo.ReportVO;

public interface IReportDao {

	public List<ReportVO> getReport(ReportVO report);
	
	
	public List<HashMap<String,String>> getCost(ReportVO report);
	
}