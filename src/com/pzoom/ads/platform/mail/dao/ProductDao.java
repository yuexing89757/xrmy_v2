package com.pzoom.ads.platform.mail.dao;

import java.util.List;

import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.mail.model.EmailTask;
import com.pzoom.ads.platform.mail.model.Product;
import com.pzoom.ads.platform.mail.type.ProductType;

public interface ProductDao {
	public List<Product> findByPage(ProductType productType,Paging paging);

}
