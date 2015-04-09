package com.pzoom.ads.platform.mail.biz.impl;

import java.util.List;

import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.mail.biz.INewsBiz;
import com.pzoom.ads.platform.mail.dao.NewsDao;
import com.pzoom.ads.platform.mail.dao.impl.NewsDaoImpl;
import com.pzoom.ads.platform.mail.model.News;
import com.pzoom.ads.platform.mail.type.NewsType;

public class NewsBizImpl implements INewsBiz{

	NewsDao  newDao=null;
	
	
	public NewsBizImpl() {
		super();
		newDao=new NewsDaoImpl();
		
	}

	public List<News> findProduct(NewsType newsType,Paging paging) {
		List<News> list=newDao.findByPage(newsType, paging);
		return list;
	}

}
