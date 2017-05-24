package com.njfu.surveypark.util;

import java.security.MessageDigest;
import java.util.Set;

import com.njfu.surveypark.model.BaseEntity;

/**
 * ����
 */
public class DataUtil {
	/**
	 * ʹ��md5�㷨���м��� 
	 */
	public static String md5(String src){
		try {
			StringBuffer buffer = new StringBuffer();
			char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			byte[] bytes = src.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] targ = md.digest(bytes);
			for(byte b: targ){
				buffer.append(chars[(b >> 4) & 0x0F]);
				buffer.append(chars[b & 0x0F]);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}

	/**
	 * ��ȡʵ���id,�γ��ַ���
	 */
	public static String extractRightIds(Set<? extends BaseEntity> entites){
		String temp = "" ;
		if(ValidateUtil.isValid(entites)){
			for(BaseEntity e: entites){
				temp = temp + e.getId() + "," ;
			}
			return temp.substring(0,temp.length() - 1);
		}
		return temp ;
	}

}