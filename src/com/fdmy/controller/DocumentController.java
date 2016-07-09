package com.fdmy.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.fdmy.model.Document;
import com.fdmy.model.User;
import com.fdmy.service.IDocumentService;
import com.fdmy.util.FileType;

import application.dcs.Convert;

/*
 * 培训管理控制器
 */
@Controller("documentController")
@RequestMapping("/train")
public class DocumentController {
	private User loginuser = null;
	private IDocumentService documentService;

	public IDocumentService getDocumentService() {
		return documentService;
	}

	@Resource
	public void setDocumentService(IDocumentService documentService) {
		this.documentService = documentService;
	}

	public DocumentController() {

	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		this.loginuser = (User) session.getAttribute("loginuser");
		return "/train/trainindex";
	}

	@RequestMapping("/documentindex")
	public String toQuery(HttpServletRequest request, Document doc, Model model) {

		HttpSession session = request.getSession();
		this.loginuser = (User) session.getAttribute("loginuser");
		model.addAttribute("document", doc);
		return "/train/documentindex";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(Model model) {
		Document doc = new Document();
		doc.setUploadtime(Calendar.getInstance().getTime());
		doc.setUploader(this.loginuser.getUsername());
		model.addAttribute("document", doc);
		model.addAttribute("status", "add");
		return "/train/documentpage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Document doc, Model model, MultipartFile attch, HttpServletRequest request) throws IOException {
		String realpath = request.getSession().getServletContext().getRealPath("/uploadfiles");
		File file = new File(realpath + "/" + attch.getOriginalFilename());
		FileUtils.copyInputStreamToFile(attch.getInputStream(), file);
		System.out.println("========文件类型===========");
		System.out.println(attch.getContentType());
		System.out.println("============================");
		String filetype = attch.getContentType();
		// 视频类
		if (filetype.startsWith("video")) {
			filetype = FileType.VIDEO;
		} else if (filetype.startsWith("application")) {
			// PPT
			if (filetype.contains("powerpoint") || filetype.contains("vnd.openxmlformats-officedocument.presentationml.presentation")) {
				filetype = FileType.PPT;
				// word
			} else if (filetype.contains("msword") || filetype.contains("vnd.openxmlformats-officedocument.wordprocessingml.document")) {
				filetype = FileType.DOC;
				// 其他的暂时都按文档处理
			} else if (filetype.contains("vnd.ms-excel") || filetype.contains("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				filetype = FileType.XLS;
				// 其他的暂时都按文档处理
			} else {
				filetype = FileType.OTHER;
			}
		}
		doc.setFiletype(filetype);
		doc.setPath(realpath);
		doc.setFilename(attch.getOriginalFilename());
		documentService.add(doc);
		model.addAttribute("document", doc);
		return "redirect:/train/query?title=" + doc.getTitle();
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(Document doc, Model model) {
		List<Document> list = documentService.query(doc);
		model.addAttribute("documentList", list);
		return "/train/documentindex";
	}

	@RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
	public String view(@PathVariable String id, Model model) throws Exception {
		Document doc = documentService.load(id);
		model.addAttribute("document", doc);
		model.addAttribute("status", "view");
		return "/train/documentpage";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String update(@PathVariable String id, Model model) throws Exception {
		Document doc = documentService.load(id);
		model.addAttribute("document", doc);
		model.addAttribute("status", "update");
		return "/train/documentpage";
	}

	// RequestMapping的value中指定的id参数，可以当作表单中传递的值对待，此处自动赋值到对象中的id字段
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(@PathVariable String id, Document doc) throws Exception {
		documentService.update(doc);
		return "redirect:/train/load?id=" + doc.getId();
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String id) throws Exception {
		documentService.delete(id);
		return "redirect:/train/query";
	}

	@RequestMapping(value = "/{id}/preview", method = RequestMethod.GET)
	public String preview(@PathVariable String id, String filename, HttpServletRequest request, Model model) throws Exception {
		Document doc = documentService.load(id);
		// 视频类文档转入预览页面
		if (doc.getFiletype().equals("video")) {
			model.addAttribute("path", doc.getPath());
			model.addAttribute("filename", doc.getFilename());
			return "/train/jwplayer";
		}
		String sourceFileName = doc.getPath() + "\\" + doc.getFilename();
		String targetFileName = doc.getPath() + "\\" + doc.getFilename() + ".html";
		Convert convert = new Convert();
		/*
		 * 返回值： 0 转换成功 1：传入的文件，找不到 2：传入的文件，打开失败 3：转换过程异常失败 4：传入的文件有密码
		 * 5：targetFileName的后缀名错误
		 */

		int result = convert.convertMStoHTML(sourceFileName, targetFileName);
		switch (result) {
		case 0:
			System.out.println("转换成功");
			break;
		case 1:
			System.out.println("传入的文件，找不到");
			break;
		case 2:
			System.out.println("传入的文件，打开失败");
			break;
		case 3:
			System.out.println("转换过程异常失败");
			break;
		case 4:
			System.out.println("传入的文件有密码");
			break;
		case 5:
			System.out.println("targetFileName的后缀名错误");
			break;
		}

		return "redirect:/uploadfiles/" + java.net.URLEncoder.encode(doc.getFilename(), "UTF-8") + ".html";
	}
}
