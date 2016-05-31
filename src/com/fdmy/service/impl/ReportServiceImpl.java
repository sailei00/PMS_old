package com.fdmy.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fdmy.controller.vo.ReportVO;
import com.fdmy.dao.IReportDao;
import com.fdmy.service.IReportService;

@Service("reportService")
public class ReportServiceImpl implements IReportService {

	private IReportDao reportDao;

	public IReportDao getReportDao() {
		return reportDao;
	}

	@Resource
	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	public List<ReportVO> getReport(ReportVO report) {
		return reportDao.getReport(report);
	}

	@Override
	public List<HashMap<String,String>> getCost(ReportVO report) {
		return reportDao.getCost(report);
	}

}
