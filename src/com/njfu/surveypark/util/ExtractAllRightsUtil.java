package com.njfu.surveypark.util;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njfu.surveypark.service.RightService;

public class ExtractAllRightsUtil {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		RightService rs = (RightService) ac.getBean("rightService");
		
		ClassLoader loader = ExtractAllRightsUtil.class.getClassLoader();
		URL url = loader.getResource("com/njfu/surveypark/struts2/action");
		File dir = new File(url.toURI());
		File[] files = dir.listFiles();
		String fname = "" ;
		for(File f : files){
			fname = f.getName();
			if(fname.endsWith(".class")
					&& !fname.equals("BaseAction.class")){
				processAction(fname,rs);
				
			}
		}
		System.out.println("Ȩ��url �������");
	}
	
	/**
	 * ����action��,��������url��ַ,�γ�Ȩ��
	 */
	@SuppressWarnings("rawtypes")
	private static void processAction(String fname,RightService rs) {
		try {
			String pkgName = "com.njfu.surveypark.struts2.action" ;
			String simpleClassName = fname.substring(0, fname.indexOf(".class"));
			String className = pkgName  + "." + simpleClassName ;
			//�õ�������
			Class clazz = Class.forName(className);
			Method[] methods = clazz.getDeclaredMethods();
			Class retType = null ;
			String mname = null ;
			Class[] paramType = null ;
			String url = null ;
			for(Method m : methods){
				retType = m.getReturnType();//����ֵ����
				mname = m.getName();        //��������
				paramType = m.getParameterTypes();//��������
				if(retType == String.class
						&& !ValidateUtil.isValid(paramType)
						&& Modifier.isPublic(m.getModifiers())){
					if(mname.equals("execute")){
						url = "/" + simpleClassName ;
					}
					else{
						url = "/" + simpleClassName + "_" + mname ;
					}
					rs.appendRightByURL(url);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
