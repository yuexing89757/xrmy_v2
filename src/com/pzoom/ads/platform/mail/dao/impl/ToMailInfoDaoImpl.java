/**    
 * Project name:ads-email
 *
 * Copyright Pzoomtech.com 2011, All Rights Reserved.
 *  
 */
package com.pzoom.ads.platform.mail.dao.impl;

import com.pzoom.ads.platform.mail.dao.ToMailInfoDao;
import com.pzoom.ads.platform.mail.dao.hibernate.util.GenericHibernateDAO;
import com.pzoom.ads.platform.mail.model.ToMailInfo;

/**
 * @name ToMailInfoDaoImpl
 * 
 * @description CLASS_DESCRIPTION
 * 
 *              MORE_INFORMATION
 * 
 * @author lijing
 * 
 * @since 2011-5-6
 * 
 * @version 1.0
 */
public class ToMailInfoDaoImpl extends GenericHibernateDAO<ToMailInfo, Integer>
		implements ToMailInfoDao {

	
	public boolean addToMailInfo(ToMailInfo toMailInfo) {
		return save(toMailInfo);
	}
	
	

}
