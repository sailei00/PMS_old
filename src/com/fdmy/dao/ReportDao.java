package com.fdmy.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.fdmy.controller.vo.ReportVO;
import com.fdmy.model.Report;

@Repository("reportDao")
public class ReportDao implements IReportDao {

	private SqlSessionTemplate sessionTemplate;

	@Resource
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	@Override
	public List<ReportVO> getReport(ReportVO report) {
		List<ReportVO> list = sessionTemplate.selectList(Report.class.getName() + ".getreport", report);
		return list;
	}

	@Override
	public List<HashMap<String,String>> getCost(ReportVO report) {
		List<HashMap<String,String>> list = sessionTemplate.selectList(Report.class.getName() + ".getcost", report);
		return list;
	}

}
