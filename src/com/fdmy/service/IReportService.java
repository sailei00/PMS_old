package com.fdmy.service;

import java.util.HashMap;
import java.util.List;

import com.fdmy.controller.vo.ReportVO;

public interface IReportService {

	

	public List<ReportVO>  getReport(ReportVO report) ;
	
	public List<HashMap<String, String>>  getCost(ReportVO report) ;


}
