package com.njfu.surveypark.util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.njfu.surveypark.model.User;
import com.njfu.surveypark.model.security.Right;


/**
 * У�鹤����
 */
public class ValidateUtil {
	/**
	 * �ж��ַ�����Ч��
	 */
	public static boolean isValid(String src){
		if(src == null || "".equals(src.trim())){
			return false ;
		}
		return true ;
	}
	
	/**
	 * �жϼ��ϵ���Ч�� 
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValid(Collection col){
		if(col == null || col.isEmpty()){			
			return false ;
		}
		return true ;
	}
	
	/**
	 * �ж������Ƿ���Ч
	 */
	public static boolean isValid(Object[] arr){
		if(arr == null || arr.length == 0){
			return false ;
		}
		return true ;
	}
	
	/**
	 * �ж��Ƿ���Ȩ��
	 */

	@SuppressWarnings("unchecked")
	public static boolean hasRight(String namespace,String actionName,HttpServletRequest req){
		if(!ValidateUtil.isValid(namespace)
				|| "/".equals(namespace)){
			namespace = "" ;
		}
		//�������ӵĲ��������˵� ?xxxx
		if(actionName.contains("?")){
			actionName = actionName.substring(0, actionName.indexOf("?"));
		}
		String url = namespace + "/" + actionName ;
		HttpSession session = req.getSession();
		ServletContext sc = session.getServletContext();
		Map<String, Right> map = (Map<String, Right>) sc.getAttribute("all_rights_map");
		Right r = map.get(url);
		//������Դ?
		if(r == null || r.isCommon()){
			return true ;
		}
		else{
			User user = (User) session.getAttribute("user");
			//��½?
			if(user == null){
				return false ;
			}
			else{
				//��������Ա
				if(user.isSuperAdmin()){
					return true ;
				}
				else{
					//��Ȩ��?
					if(user.hasRight(r)){
						return true ;
					}
					else{
						return false ;
					}
				}
			}
		}
	}
}

