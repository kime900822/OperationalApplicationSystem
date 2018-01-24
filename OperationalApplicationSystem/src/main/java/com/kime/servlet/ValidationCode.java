package com.kime.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ValidationCode")
public class ValidationCode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//图形验证码的字符集合
	private static String codeChars="23456789abcdefghkmnpqrstuvwxyzABCDEFGHKLMNPQRSTUVWXYZ";
	//返回一个随机数
	private static Color getRandomColor(int minColor,int maxColor){
		Random random=new Random();
		if (minColor>255) {
			minColor=255;
		}
		if (maxColor>255) {
			maxColor=255;
		}
		int red = minColor+random.nextInt(maxColor-minColor);
		int green = minColor+random.nextInt(maxColor-minColor);
		int blue = minColor+random.nextInt(maxColor-minColor);
		return new Color(red, green, blue);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int charsLength=codeChars.length();
		//关闭浏览器的缓冲区，因为浏览器不同，所以建议同时使用
		response.setHeader("ragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//设置图形长度宽度
		int width=90,height=35;
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获得graphic
		Graphics g=image.getGraphics();
		Random random=new Random();
		g.setColor(getRandomColor(180, 250));
		g.fillRect(0, 0, width, height); //填充背景色
		g.setFont(new Font("Times New Roman", Font.ITALIC, height));
		g.setColor(getRandomColor(120, 180));
		//用于保存最后的验证码
		StringBuilder validationCode=new StringBuilder();
		//验证码的随机字体
		String[] fontNames={"Times New Roman","Book antiqua","Arial"};
		//随机生成3-5个验证码
		for(int i=0;i<3+random.nextInt(3);i++){
			g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC, height));
			char codeChar=codeChars.charAt(random.nextInt(charsLength));
			validationCode.append(codeChar);
			g.setColor(getRandomColor(10, 100));
			//在图形上输出验证码，x和y都随机生成
			g.drawString(String.valueOf(codeChar), 16*i+random.nextInt(7), height-random.nextInt(6));
		}
	
		
		Cookie cookie=new Cookie("validationcode", validationCode.toString().trim());
		//cookie.setPath("/");
		response.addCookie(cookie);
		
		g.dispose();
		OutputStream os=response.getOutputStream();
		ImageIO.write(image, "JPEG", os);
		
		
	}
	
	
	
	
	
}

