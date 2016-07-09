package com.fdmy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fdmy.dao.IDocumentDao;
import com.fdmy.model.Document;
import com.fdmy.service.IDocumentService;

@Service("documentService")
public class DocumentServiceImpl implements IDocumentService {

	private IDocumentDao documentDao;

	public IDocumentDao getDocumentDao() {
		return documentDao;
	}

	@Resource
	public void setDocumentDao(IDocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	@Override
	public void add(Document document) {
		documentDao.add(document);
	}

	@Override
	public void delete(String id) {
		documentDao.delete(id);
	}

	@Override
	public void update(Document document) {
		documentDao.update(document);
	}

	@Override
	public Document load(String id) {
		return documentDao.load(id);
	}

	@Override
	public List<Document> query(Document document) {
		return documentDao.query(document);
	}

}
