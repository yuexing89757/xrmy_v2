/**    
 * Project name:ads-mail
 *
 * Copyright Pzoomtech.com 2011, All Rights Reserved.
 *  
 */
package com.pzoom.ads.platform.mail.biz;

import java.util.List;


import com.pzoom.ads.platform.backend.core.business.Result;
import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.mail.model.News;
import com.pzoom.ads.platform.mail.model.Product;
import com.pzoom.ads.platform.mail.type.NewsType;
import com.pzoom.ads.platform.mail.type.ProductType;


public interface INewsBiz{
	public List<News> findProduct(NewsType newsType,Paging paging);
}
