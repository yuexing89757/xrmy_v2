package com.pzoom.ads.platform.mail.dao;

import java.util.List;

import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.mail.model.EmailTask;
import com.pzoom.ads.platform.mail.type.StatusType;

public interface EmailTaskDao {
	public boolean addEmailTask(EmailTask emailTask);
	public List<EmailTask>  findByPage(StatusType userStatus,String sortColumns,String sortDir,Paging paging);
	public boolean deleteEmailTask(EmailTask emailTask);
	List<EmailTask> findByPlanTimeNull(StatusType status, String sortColumns,
			String sortDir, Paging paging);
	List<EmailTask> findByPlanTimeNotNull(StatusType status, String sortColumns,
			String sortDir, Paging paging);
	List<EmailTask> findByPageRunPro(StatusType status, String sortColumns,
			String sortDir, Paging paging);
}
