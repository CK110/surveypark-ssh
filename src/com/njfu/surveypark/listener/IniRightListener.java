package com.njfu.surveypark.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.njfu.surveypark.model.security.Right;
import com.njfu.surveypark.service.RightService;

/**
 *  ��ʼ��Ȩ�޼�����,��spring��ʼ����ɺ���������.
 * @author CK
 * 2015��3��26������2:15:23
 */
@SuppressWarnings("rawtypes")
@Component
public class IniRightListener implements ApplicationListener,ServletContextAware{
	@Resource
	private RightService rightService;
	
	private ServletContext sc;//����ServletContext

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		//������ˢ���¼�
		if(arg0 instanceof ContextRefreshedEvent){
			//�������Ȩ��
			List<Right> rights = rightService.findAllEntities();
			Map<String,Right> map = new HashMap<String,Right>();
			for(Right r : rights){
				map.put(r.getRightUrl(),r);
			}
			if(sc != null){
				sc.setAttribute("all_rights_map", map);
				System.out.println("��ʼ������Ȩ�޵�Application��!!!!");
			}			
		}
		
	}

	/**
	 *ע��ServletContext 
	 */
	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0 ;	
	}
	
	

}
