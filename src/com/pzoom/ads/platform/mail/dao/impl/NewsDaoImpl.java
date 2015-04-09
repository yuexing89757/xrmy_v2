package com.pzoom.ads.platform.mail.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.pzoom.ads.platform.backend.dao.config.GenericHbmDAO;
import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.mail.dao.NewsDao;
import com.pzoom.ads.platform.mail.model.News;
import com.pzoom.ads.platform.mail.type.NewsType;

public class NewsDaoImpl extends GenericHbmDAO<News, Long> implements NewsDao {

	public List<News> findByPage(NewsType newsType, Paging paging) {
		Criteria criteria = this.createCriteria();
		if(null!=newsType){
			criteria.add(Restrictions.eq("newsType",newsType));
		}else{
			criteria.add(Restrictions.not(Restrictions.isNull("newsType")));
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
