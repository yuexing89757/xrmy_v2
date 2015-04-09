package com.pzoom.ads.job;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.junit.Test;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pzoom.ads.platform.backend.core.UncheckedException;
import com.pzoom.ads.platform.backend.core.dao.TransactionStrategy;
import com.pzoom.ads.platform.backend.core.scheduler.SchedulableJob;
import com.pzoom.ads.platform.backend.dao.config.HibernateTransactionStrategy;
import com.pzoom.ads.platform.backend.util.Log;
import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.mail.biz.ISendMail;
import com.pzoom.ads.platform.mail.biz.impl.SendCloudMail;
import com.pzoom.ads.platform.mail.biz.impl.SendMail;
import com.pzoom.ads.platform.mail.dao.EmailTaskDao;
import com.pzoom.ads.platform.mail.dao.impl.EmailTaskDaoImpl;
import com.pzoom.ads.platform.mail.model.EmailTask;
import com.pzoom.ads.platform.mail.type.StatusType;
import com.pzoom.ads.platform.mail.util.LanguageKey;
import com.pzoom.ads.platform.mail.util.StringUtils;

public class AutoSendMailJob extends SchedulableJob {
	private Log log = Log.getLogger(AutoSendMailJob.class);
	EmailTaskDao eamilTaskDao = new EmailTaskDaoImpl();

	public void execute(JobExecutionContext jobContext) {
		Paging paging = new Paging();
		paging.setCurrentPage(0);
		paging.setPageSize(10);
		List<EmailTask> resultList=null;
		do{
			resultList= getTaskDB(paging);
			if (null != resultList && resultList.size()>0) {
				TransactionStrategy transaction = HibernateTransactionStrategy.getInstance();
				transaction.begin();
				try{
					for (EmailTask emailTask : resultList) {
						 emailTask=sendMail(emailTask);
						if(StringUtils.hasText(emailTask.getFailcode())){
							if(null!=emailTask.getToMailInfo().getPlanTime()){
								emailTask.setFailcode(null);
								emailTask.setStatus(StatusType.READY);
								eamilTaskDao.addEmailTask(emailTask);
							}else{
							    eamilTaskDao.addEmailTask(emailTask);
							}
						}else{
							eamilTaskDao.deleteEmailTask(emailTask);
						}
					}
					transaction.commit();
				}catch(Exception e){
					transaction.rollback();
					log.error(e);
				}
			}
			resultList= getTaskDB(paging);
		}while(null!=resultList && resultList.size()>0);
	}

	public static void main(String[] args) throws JobExecutionException {
		AutoSendMailJob job = new AutoSendMailJob();
		job.execute(null);
	}

	public synchronized List<EmailTask> getTaskDB(Paging paging) {
		List<EmailTask> resultList = eamilTaskDao.findByPlanTimeNull(StatusType.READY, "id", "ASC",paging);
		List<EmailTask> resultNotNull = eamilTaskDao.findByPlanTimeNotNull(StatusType.READY, "id", "ASC",paging);
		resultList.addAll(resultNotNull);
		TransactionStrategy transaction = HibernateTransactionStrategy.getInstance();
		transaction.begin();
		try{
			for(EmailTask emailTask :resultList){
				emailTask.setFunctionTime(new Date());
				emailTask.setStatus(StatusType.FUNCTION);
				eamilTaskDao.addEmailTask(emailTask);
			}
			transaction.commit();
			return resultList;
		}catch(Exception e){
			log.error(e);
			transaction.rollback();
		}
		return null;
	}
	
	
	
	public  EmailTask  sendMail(EmailTask emailTask){
		ISendMail sendmail = new SendCloudMail();
		//如果是易尔通邮件服务，则用原来的common-mail组件发送
		if ("7".equals(emailTask.getMailUserInfo().getClassify())) {
			sendmail = new SendMail();
		}
		
		try {
			
			if(null!=emailTask.getToMailInfo().getPlanTime()){
				if(emailTask.getToMailInfo().getPlanTime().before(new Date())){
					sendmail.sendMail(StringToArray(emailTask.getToMailInfo().getReceiver()), 
					          emailTask.getToMailInfo().getTitle(), 
					          emailTask.getToMailInfo().getMailContent(), 
					          StringToArray(emailTask.getToMailInfo().getAttachPath()), 
					          StringToArray(emailTask.getToMailInfo().getAttachDescription()), 
					          StringToArray(emailTask.getToMailInfo().getAttachName()), 
					          StringToArray(emailTask.getToMailInfo().getCopySend()), 
					          StringToArray(emailTask.getToMailInfo().getBlindSend()),
					          emailTask.getToMailInfo().getMailType().toString(), 
					          emailTask.getMailUserInfo().getClassify());
				}else{
					throw new UncheckedException(LanguageKey.NOT_PLANTIME,"001");
				}
				
			}else{
				sendmail.sendMail(StringToArray(emailTask.getToMailInfo().getReceiver()), 
				          emailTask.getToMailInfo().getTitle(), 
				          emailTask.getToMailInfo().getMailContent(), 
				          StringToArray(emailTask.getToMailInfo().getAttachPath()), 
				          StringToArray(emailTask.getToMailInfo().getAttachDescription()), 
				          StringToArray(emailTask.getToMailInfo().getAttachName()), 
				          StringToArray(emailTask.getToMailInfo().getCopySend()), 
				          StringToArray(emailTask.getToMailInfo().getBlindSend()),
				          emailTask.getToMailInfo().getMailType().toString(), 
				          emailTask.getMailUserInfo().getClassify());
			}
			
		} catch (EmailException e) {
			e.printStackTrace();
			emailTask.setFailcode(e.toString());
		} catch (Exception e) {
			emailTask.setFailcode(e.toString());
		}
		return emailTask; 
	}
	
	public static String[] StringToArray(String str){
		List<String> ps =null;
		if(StringUtils.hasLength(str)){
			ps=new Gson().fromJson(str,new TypeToken<List<String>>(){}.getType());
			return ps.toArray(new String[ps.size()]);
		}
		return null;
	}
	
	@Test
	public  void  test(){
		AutoSendMailJob job = new AutoSendMailJob();
		job.execute(null);
	}

}
