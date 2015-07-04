package com.MutiModule.common.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class FileUploadServlet extends HttpServlet {
	
	private final static String uploadDir = "downloads";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8"); // 防止中文乱码
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List items = upload.parseRequest(req);
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    System.out.println("表单参数名：" + item.getFieldName() + ",表单参数值：" + item.getString("UTF-8"));
                } else {
                    if (item.getName() != null && !item.getName().equals("")) {
                        System.out.println("上传文件的大小：" + item.getSize());
                        System.out.println("文件的类型：" + item.getContentType());
                        System.out.println("上传文件的名称：" + item.getName());// 返回上传文件在客户端的完整路径名称
                        
                        File folder = new File(req.getServletContext().getRealPath("/") + File.separatorChar + uploadDir);
                        if(!folder.exists() || !folder.isDirectory()) {
                        	folder.mkdirs();
                        }

                        File file = new File(req.getServletContext().getRealPath("/") + File.separatorChar + uploadDir + File.separatorChar
                                + item.getName());
                        item.write(file);
                        
                        resp.setContentType("text/html;charset=UTF-8");//必须的设置，返回串类型指定json
                        String retxt ="{\"msg\":\""+file.getPath().replace(File.separatorChar+"", ""+File.separatorChar+File.separator)+"\",\"success\":\"true\"}";  
                        resp.getWriter().print(retxt);
                    } else {
                    	resp.setContentType("text/html;charset=UTF-8");//必须的设置，返回串类型指定json
                        String retxt ="{\"success\":\"false\", \"msg\":\"没有选择文件\"}";  
                        resp.getWriter().print(retxt);
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
			resp.setContentType("text/html;charset=UTF-8");//必须的设置，返回串类型指定json
            String retxt = "{\"success\":\"false\", ,\"msg\":\"上传文件失败\"}";  
            resp.getWriter().print(retxt);
        }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
