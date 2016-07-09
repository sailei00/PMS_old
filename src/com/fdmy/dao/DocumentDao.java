package com.fdmy.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.fdmy.model.Document;
import com.fdmy.util.FileType;
import com.fdmy.util.SystemContext;
import com.github.pagehelper.PageHelper;

@Repository("documentDao")
public class DocumentDao implements IDocumentDao {
	private HashMap<String, String> iconmap;
	private SqlSessionTemplate sessionTemplate;

	@Resource
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public DocumentDao() {
		iconmap = new HashMap<String, String>();
		iconmap.put(FileType.DOC, "/images/filetype/word.png");
		iconmap.put(FileType.PPT, "/images/filetype/ppt.png");
		iconmap.put(FileType.VIDEO, "/images/filetype/video.png");
		iconmap.put(FileType.OTHER, "/images/filetype/other.png");
	}

	@Override
	public void add(Document document) {
		sessionTemplate.insert(Document.class.getName() + ".add", document);
	}

	@Override
	public void delete(String code) {
		sessionTemplate.delete(Document.class.getName() + ".delete", code);
	}

	@Override
	public void update(Document document) {
		sessionTemplate.update(Document.class.getName() + ".update", document);
	}

	@Override
	public Document load(String id) {
		Document doc = sessionTemplate.selectOne(Document.class.getName() + ".load", id);
		doc.setIcon(iconmap.get(doc.getFiletype()));
		return doc;
	}

	@Override
	public List<Document> query(Document document) {
		int pageSize = SystemContext.getPageSize();
		int pageNo = SystemContext.getPageNo();
		PageHelper.startPage(pageNo, pageSize);
		List<Document> list = sessionTemplate.selectList(Document.class.getName() + ".query", document);

		return list;
	}

}
