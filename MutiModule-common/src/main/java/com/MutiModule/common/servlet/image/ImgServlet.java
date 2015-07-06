package com.MutiModule.common.servlet.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MutiModule.common.utils.ImageUtilss;
import com.MutiModule.common.utils.StringUtilss;

public class ImgServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String fileRealPath = req.getRealPath(req.getRequestURI().replace(req.getContextPath(), ""));

		File requestFile = new File(fileRealPath);

		if (requestFile.exists()) {
			
			responseImg(fileRealPath, resp);
			
		} else {
			String fileFolder = fileRealPath.substring(0, fileRealPath.lastIndexOf(File.separator) + 1);
			String _fileName = fileRealPath.substring(fileRealPath.lastIndexOf(File.separator) + 1);
			
			String fileExtName = StringUtilss.getFileExt(_fileName);
			String fileName = StringUtilss.getFileName(_fileName);
			
			if(fileName.contains("!")) {
				
				try {
					String[] _extendFileName = fileName.split("!");
					String[] _widthAndheight = _extendFileName[1].split("_");
					
					String existedFilePath = fileRealPath.substring(0, fileRealPath.lastIndexOf(File.separatorChar) +1 ) +  _extendFileName[0] + "." + fileExtName;
					
					ImageUtilss.cutImage(Integer.parseInt(_widthAndheight[0]), Integer.parseInt(_widthAndheight[1]), existedFilePath, fileRealPath);
					
					responseImg(fileRealPath, resp);
					
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * servlet 返回图片信息
	 * @param fileRealPath 图片的真实路径
	 * @param resp	相应报文
	 */
	private void responseImg(String fileRealPath, HttpServletResponse resp) {
		try {
			FileInputStream fis = new FileInputStream(fileRealPath);
			int size = fis.available(); // 得到文件大小
			byte data[] = new byte[size];
			fis.read(data); // 读数据
			fis.close();
			resp.setContentType("image/gif"); // 设置返回的文件类型
			OutputStream os = resp.getOutputStream();
			os.write(data);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
