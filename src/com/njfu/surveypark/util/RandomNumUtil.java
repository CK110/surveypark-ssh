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
	
	private ByteArrayInputStream image;//ͼ��   
	private String str;//��֤��   
		       		   
	private RandomNumUtil(){   
		init();//��ʼ������   
	}   
    /*  
     * ȡ��RandomNumUtilʵ��  
     */  
	 public static RandomNumUtil Instance(){   
		 return new RandomNumUtil();   
	}   	 
	 /*  
	  * ȡ����֤��ͼƬ  
	  */  
	 public ByteArrayInputStream getImage(){   
		 return this.image;   
	 }   
	 /*  
	  * ȡ��ͼƬ����֤��  
	  */  
	 public String getString(){   
		 return this.str;   
	 }   
		       
	 private void init() {   
		 //       ���ڴ��д���ͼ��   
		 int width=100, height=37;   
		 BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
		 //       ��ȡͼ��������   
		 Graphics g = image.getGraphics();   
		 //      ���������   
		 Random random = new Random();   
		 //       �趨����ɫ   
		 g.setColor(Color.WHITE);
		 g.fillRect(0,0, width, height);   
		 //      �趨����   
		 g.setFont(new Font("Times New Roman",Font.PLAIN,18));    		 
		 //ȡ�����������֤��(5λ)   
		 String sRand="";   
		 char selectChar[] = {'0','1','2','3','4','5','6','7','8','9',
		          'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		          'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};//���к�ѡ�����֤����ַ�����ȻҲ���������ĵ�
		 for (int i=0;i<5;i++){   
			 int charIndex = (int) Math.floor(Math.random()*62);
		     String rand = String.valueOf(selectChar[charIndex]);
		     sRand +=selectChar[charIndex];
		     // ����֤����ʾ��ͼ����   
		     g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));   
		     // ���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������   
		     g.drawString(rand,13*i+6,16);   
		     this.str=sRand;/*   ��ֵ��֤��   */  
		 }   
		 //ͼ����Ч   
		 g.dispose(); 
		 
		 ByteArrayInputStream input=null;   
		 ByteArrayOutputStream output = new ByteArrayOutputStream();   
		 try{   
			 ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);   
			 ImageIO.write(image, "JPEG", imageOut);   
			 imageOut.close();   
			 input = new ByteArrayInputStream(output.toByteArray());   
		 }catch(Exception e){   
			 System.out.println("��֤��ͼƬ�������ִ���"+e.toString());   
		 }   
		           
		 this.image=input;/*  ��ֵͼ��  */  
		 }     
}

