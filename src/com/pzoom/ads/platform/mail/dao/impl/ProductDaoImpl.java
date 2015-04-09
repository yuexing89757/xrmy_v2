package com.pzoom.ads.platform.mail.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.pzoom.ads.platform.backend.dao.config.GenericHbmDAO;
import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.mail.dao.ProductDao;
import com.pzoom.ads.platform.mail.model.Product;
import com.pzoom.ads.platform.mail.type.ProductType;

public class ProductDaoImpl extends GenericHbmDAO<Product, Long> implements ProductDao {

	public List<Product> findByPage(ProductType productType, Paging paging) {
		Criteria criteria = this.createCriteria();
		if(null!=productType){
			criteria.add(Restrictions.eq("productType",productType));
		}else{
			criteria.add(Restrictions.not(Restrictions.isNull("productType")));
		}
		if (paging != null) {
			paging.setTotalRecord(getTotalRecords(criteria));
			criteria.setProjection(null);
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			criteria.setFirstResult(paging.getCurrentRecord()).setMaxResults(paging.getPageSize());
		}
		return criteria.list();
	}

	

}
