package org.hwi.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@ToString(exclude="pageList")
@Log
public class PageMaker<T> {

	private Page<T> result;
	
	private Pageable prevPage;
	private Pageable nextPage;
	
	private int currentPageNum;
	private int totalPageNum; 
	
	private Pageable currentPage;
	
	private List<Pageable> pageList;
	
	public PageMaker(Page<T> result){
		
		this.result = result;
		
		this.currentPage = result.getPageable();
		
		this.currentPageNum = currentPage.getPageNumber() + 1; 
		
		this.totalPageNum = result.getTotalPages();
		
		this.pageList = new ArrayList<>();
		
		calcPages();
		
	}
	private void calcPages(){
		
		int tempEndNum = (int)(Math.ceil(this.currentPageNum/10.0)* 10);
		
		int startNum = tempEndNum -9; 
		
		Pageable startPage = this.currentPage;
		
		//move to start Pageble 
		for(int i = startNum; i < this.currentPageNum; i++){
			startPage = startPage.previousOrFirst();
		}
		this.prevPage = startPage.getPageNumber() <= 0? null :startPage.previousOrFirst();
		
//		log.info("tempEndNum: " + tempEndNum);
//		log.info("total: "+ totalPageNum);
		
		if(this.totalPageNum < tempEndNum){
			tempEndNum = this.totalPageNum;
			this.nextPage = null;
		}
		
		for(int i = startNum ; i <= tempEndNum; i++){
			pageList.add(startPage);
			startPage = startPage.next();
		}
		this.nextPage = startPage.getPageNumber() +1 < totalPageNum ? startPage: null;
		
	}
	
	
}
