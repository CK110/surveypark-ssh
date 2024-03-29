package com.njfu.surveypark.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class RandomNumUtil {
	
	private ByteArrayInputStream image;//图像   
	private String str;//验证码   
		       		   
	private RandomNumUtil(){   
		init();//初始化属性   
	}   
    /*  
     * 取得RandomNumUtil实例  
     */  
	 public static RandomNumUtil Instance(){   
		 return new RandomNumUtil();   
	}   	 
	 /*  
	  * 取得验证码图片  
	  */  
	 public ByteArrayInputStream getImage(){   
		 return this.image;   
	 }   
	 /*  
	  * 取得图片的验证码  
	  */  
	 public String getString(){   
		 return this.str;   
	 }   
		       
	 private void init() {   
		 //       在内存中创建图象   
		 int width=100, height=37;   
		 BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
		 //       获取图形上下文   
		 Graphics g = image.getGraphics();   
		 //      生成随机类   
		 Random random = new Random();   
		 //       设定背景色   
		 g.setColor(Color.WHITE);
		 g.fillRect(0,0, width, height);   
		 //      设定字体   
		 g.setFont(new Font("Times New Roman",Font.PLAIN,18));    		 
		 //取随机产生的认证码(5位)   
		 String sRand="";   
		 char selectChar[] = {'0','1','2','3','4','5','6','7','8','9',
		          'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		          'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};//所有候选组成验证码的字符，当然也可以用中文的
		 for (int i=0;i<5;i++){   
			 int charIndex = (int) Math.floor(Math.random()*62);
		     String rand = String.valueOf(selectChar[charIndex]);
		     sRand +=selectChar[charIndex];
		     // 将认证码显示到图象中   
		     g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));   
		     // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成   
		     g.drawString(rand,13*i+6,16);   
		     this.str=sRand;/*   赋值验证码   */  
		 }   
		 //图象生效   
		 g.dispose(); 
		 
		 ByteArrayInputStream input=null;   
		 ByteArrayOutputStream output = new ByteArrayOutputStream();   
		 try{   
			 ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);   
			 ImageIO.write(image, "JPEG", imageOut);   
			 imageOut.close();   
			 input = new ByteArrayInputStream(output.toByteArray());   
		 }catch(Exception e){   
			 System.out.println("验证码图片产生出现错误："+e.toString());   
		 }   
		           
		 this.image=input;/*  赋值图像  */  
		 }     
}

