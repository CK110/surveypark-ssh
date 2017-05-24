package com.njfu.surveypark.util;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {

	public static int getTotalPage(int totalSize,int pageSize){
		
		if (totalSize % pageSize == 0) {
		   return totalSize / pageSize;
		} else {
		    return totalSize / pageSize + 1;
		}
	}
	
	public static List<Integer> getPageList(int totalPage){
		List<Integer> pageList = new ArrayList<Integer>();
		for (int i = 1; i <= totalPage; i++) {
			pageList.add(i);
		}
		return pageList;
	}
}
