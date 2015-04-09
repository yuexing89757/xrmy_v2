package com.pzoom.ads.platform.mail.model;

import java.util.Date;

import com.pzoom.ads.platform.backend.model.ModelObject;
import com.pzoom.ads.platform.mail.type.StatusType;

public class EmailTask extends ModelObject<Long> {
    private StatusType status;
    private String failcode;
    private MailUserInfo mailUserInfo;
    private Date createTime;
    private Date functionTime;
    private ToMailInfo toMailInfo;
    
	
	public StatusType getStatus() {
		return status;
	}
	public void setStatus(StatusType status) {
		this.status = status;
	}
	public String getFailcode() {
		return failcode;
	}
	public void setFailcode(String failcode) {
		this.failcode = failcode;
	}
	
	public MailUserInfo getMailUserInfo() {
		return mailUserInfo;
	}
	public void setMailUserInfo(MailUserInfo mailUserInfo) {
		this.mailUserInfo = mailUserInfo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getFunctionTime() {
		return functionTime;
	}
	public void setFunctionTime(Date functionTime) {
		this.functionTime = functionTime;
	}
	

	public ToMailInfo getToMailInfo() {
		return toMailInfo;
	}
	public void setToMailInfo(ToMailInfo toMailInfo) {
		this.toMailInfo = toMailInfo;
	}
	public EmailTask() {
		super();
	}
	
}
