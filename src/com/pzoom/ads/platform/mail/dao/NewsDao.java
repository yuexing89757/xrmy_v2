package com.pzoom.ads.platform.mail.dao;

import java.util.List;

import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.mail.model.EmailTask;
import com.pzoom.ads.platform.mail.model.News;
import com.pzoom.ads.platform.mail.model.Product;
import com.pzoom.ads.platform.mail.type.NewsType;
import com.pzoom.ads.platform.mail.type.ProductType;

public interface NewsDao {
	public List<News> findByPage(NewsType newsType,Paging paging);

}
