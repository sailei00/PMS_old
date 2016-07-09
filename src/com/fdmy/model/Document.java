package com.fdmy.model;

import java.util.Date;

public class Document {
	/*
	 * id int not null auto_increment, title varchar(255), filetype varchar(255)
	 * comment 'doc,ppt,video', category varchar(255), uploader varchar(255),
	 * uploadtime datetime, path varchar(1000),
	 */
	private String id;
	private String title;
	private String filetype;
	private String category;
	private String uploader;
	private Date uploadtime;
	private String path;
	private String filename;
	private String icon;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = "".equals(title) ? null : title;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = "".equals(filetype) ? null : filetype;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = "".equals(category) ? null : category;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = "".equals(uploader) ? null : uploader;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = "".equals(path) ? null : path;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
