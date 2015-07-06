package com.MutiModule.common.utils;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

public class ImageUtilss {

	/**
	 * ImageMagick的路径
	 */
	public static String imageMagickPath = null;
	
	static {
		/**
		 * 
		 * 获取ImageMagick的路径
		 */
		Object imageMagicPathObj = PropertiesUtilss.getProperties("imageMagickPath.properties", "imageMagickPath");
		
		// linux下不要设置此值，不然会报错
		if (imageMagicPathObj != null) {
			imageMagickPath = imageMagicPathObj.toString();
		}
	}

	/**
	 * 
	 * 根据坐标裁剪图片
	 * 
	 * @param srcPath
	 *            要裁剪图片的路径
	 * @param newPath
	 *            裁剪图片后的路径
	 * @param x
	 *            起始横坐标
	 * @param y
	 *            起始纵坐标
	 * @param x1
	 *            结束横坐标
	 * @param y1
	 *            结束纵坐标
	 */

	public static void cutImage(String srcPath, String newPath, int x, int y, int x1, int y1) throws Exception {
		int width = x1 - x;
		int height = y1 - y;
		IMOperation op = new IMOperation();
		op.addImage(srcPath);
		/**
		 * width： 裁剪的宽度 height： 裁剪的高度 x： 裁剪的横坐标 y： 裁剪的挫坐标
		 */
		op.crop(width, height, x, y);
		op.addImage(newPath);
		ConvertCmd convert = new ConvertCmd(true);

		// linux下不要设置此值，不然会报错
		if (imageMagickPath != null) {
			convert.setSearchPath(imageMagickPath);
		}

		convert.run(op);
	}

	/**
	 * 
	 * 根据尺寸缩放图片
	 * 
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @param srcPath
	 *            源图片路径
	 * @param newPath
	 *            缩放后图片的路径
	 */
	public static void cutImage(int width, int height, String srcPath, String newPath) throws Exception {
		IMOperation op = new IMOperation();
		op.addImage(srcPath);
		op.resize(width, height);
		op.addImage(newPath);
		ConvertCmd convert = new ConvertCmd(true);
		// linux下不要设置此值，不然会报错
		convert.setSearchPath(imageMagickPath);
		convert.run(op);

	}

	/**
	 * 根据宽度缩放图片
	 * 
	 * @param width
	 *            缩放后的图片宽度
	 * @param srcPath
	 *            源图片路径
	 * @param newPath
	 *            缩放后图片的路径
	 */
	public static void cutImage(int width, String srcPath, String newPath) throws Exception {
		IMOperation op = new IMOperation();
		op.addImage(srcPath);
		op.resize(width, null);
		op.addImage(newPath);
		ConvertCmd convert = new ConvertCmd(true);
		// linux下不要设置此值，不然会报错
		convert.setSearchPath(imageMagickPath);
		convert.run(op);
	}

}
