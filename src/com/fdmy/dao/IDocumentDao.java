package com.fdmy.dao;

import java.util.List;

import com.fdmy.model.Document;

public interface IDocumentDao {

	public void add(Document document);

	public void delete(String id);

	public void update(Document document);

	public Document load(String id);

	public List<Document> query(Document document);

}
