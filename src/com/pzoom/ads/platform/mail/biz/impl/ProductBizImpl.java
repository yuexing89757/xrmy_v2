package com.pzoom.ads.platform.mail.biz.impl;

import java.util.List;

import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.mail.biz.IProductBiz;
import com.pzoom.ads.platform.mail.dao.ProductDao;
import com.pzoom.ads.platform.mail.dao.impl.ProductDaoImpl;
import com.pzoom.ads.platform.mail.model.Product;
import com.pzoom.ads.platform.mail.type.ProductType;

public class ProductBizImpl implements IProductBiz{

	ProductDao  productDao=null;
	
	
	public ProductBizImpl() {
		super();
		productDao=new ProductDaoImpl();
		
	}

	public List<Product> findProduct(ProductType productType,Paging paging) {
		List<Product> list=productDao.findByPage(productType, paging);
		return list;
	}

}
