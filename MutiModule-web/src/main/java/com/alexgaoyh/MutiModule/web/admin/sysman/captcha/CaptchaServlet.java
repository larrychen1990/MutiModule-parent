package com.alexgaoyh.MutiModule.web.admin.sysman.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alexgaoyh.MutiModule.captcha.CaptchaUtil;
import com.alexgaoyh.MutiModule.util.redis.RedisClient;
import com.alexgaoyh.MutiModule.web.util.ConstantsUtil;


/**
 * 验证码servlet
 * @author Administrator
 *
 */
public class CaptchaServlet extends HttpServlet {
	
	private static final long serialVersionUID = -124247581620199710L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置相应类型,告诉浏览器输出的内容为图片
		resp.setContentType("image/jpeg");
		// 不缓存此内容
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expire", 0);
		try {


			CaptchaUtil tool = new CaptchaUtil();
			//code为 随机的4位整数验证码
			StringBuffer code = CaptchaUtil.randomCode(4);
			BufferedImage image = tool.genRandomCodeImage(code);
			
			RedisClient.add(ConstantsUtil.ADMIN_CAPTCHA_CONSTANTS, code.toString());
			
			// 将内存中的图片通过流动形式输出到客户端
			ImageIO.write(image, "JPEG", resp.getOutputStream());

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}