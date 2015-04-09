/**    
 * Project name:ads-emaila1
 *
 * Copyright Pzoomtech.com 2011, All Rights Reserved.
 *  
 */
package com.pzoom.ads.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pzoom.ads.email.api.EmailApi;
import com.pzoom.ads.platform.mail.model.MailUserInfo;

/**
 * @name TestWebApi
 * 
 * @description CLASS_DESCRIPTION
 * 
 *              MORE_INFORMATION
 * 
 * @author lijing
 * 
 * @since 2011-4-29
 * 
 * @version 1.0
 */
public class TestWebApi extends WebSendMailTest<EmailApi> {

	@Test
	public void testMethod() { // 测试发邮件
		this.setUp(this.getUrl("/email/filePath/send"));
		this.addParam("toMail", new String[] { "243860327@qq.com" });
		this.addParam("title", "品牌词监控提醒【测试2018】");
		this.addParam("mailContent", "尊敬的客户 您好!\n\n \t 品众分析系统提醒您，您投放的品牌词已被其他广告主投放，详细请登录系统查看.\n\t品牌词如下:\n\t\t百雀羚精纯系列");
		//this.addParam("attachName", new String[] { "附件1.xlsx"});
		//this.addParam("attachDescription", new String[] { "附件.xlsx" });
		//this.addParam("attachPath",new String[] { "https://up.adup.com.tw/downloadreport.do?fileName=test(%E5%93%81%E4%BC%97)&filePath=GOOGLE/2014/5/3083845287/fc8d5dcf-1732-4153-b976-9f3fed561d35.xlsx&reportid=fc8d5dcf-1732-4153-b976-9f3fed561d35&format=xlsx&fileType=clientreport"});
		this.addParam("mailType", "HTML");
		this.addParam("classify", "8");
		this.testResonse(this.createApiRequest());
	}

	@Test
	public void testMethod2() { // 测试发邮件
		this.setUp(this.getUrl("/email/filePath/send"));
		this.addParam("title", "找回密码");
		this.addParam("toMail", new String[] { "243860327@qq.com" });
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		this.addParam(
				"mailContent",
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
						+ "<HTML>"
						+ "<HEAD><TITLE>会员密码找回</TITLE></HEAD>"
						+ "<BODY>"
						+ "<p align='left'>亲爱的用户:</p>"
						+ "<p align='left'>&nbsp;&nbsp;&nbsp;&nbsp;请点击冒号后面的链接;如果不能点击,请将冒号后面的链接复制并粘帖到浏览器的地址输入框,然后敲回车即可。（链接有效时间为1个小时）：</p>"
						+ "<p align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://sempro-qa.pzoom.com/pzoom_forgetpassword.html</p>"
						+ "<br/><br/><p align='left'>品种互动团队</p> <p align='left'>"
						+ sdf.format(Calendar.getInstance().getTime()) + "</p>"
						+ "</BODY></HTML>");
		this.addParam("mailType", "HTML");
		this.addParam("classify", "0");
		this.testResonse(this.createApiRequest());
	}

	@Test
	public void testAddEmail() { // 测试添加邮箱
		this.setUp(this.getUrl("/email/addMailUser"));
		MailUserInfo mailUserInfo = new MailUserInfo();
		mailUserInfo.setEmailAddress("777777777@qq.com");
		mailUserInfo.setUserName("305429069");
		mailUserInfo.setPassword("888888889");
		this.addParam("mailUserInfo", mailUserInfo);
		mailUserInfo.setUserStatus("1"); //null common 1 adapter
		mailUserInfo.setSmtpUserName("postmaster@monkey.sendcloud.org");
		mailUserInfo.setClassify("11");
		this.testResonse(this.createApiRequest());
	}


	
	@Test
	public void testUpdateEmail() { // 测试更新邮箱
		this.setUp(this.getUrl("/email/updateMailUser"));
		MailUserInfo mailUserInfo = new MailUserInfo();
		mailUserInfo.setEmailAddress("oooooooo@qq.com");
		mailUserInfo.setUserName("ooooooooo@qq.com");
		mailUserInfo.setPassword("rrtre");
		mailUserInfo.setNewPassword("we");
		mailUserInfo.setClassify("15");
		mailUserInfo.setSmtpUserName("postmaster@monkey.sendcloud.org");
		mailUserInfo.setId((long) 721);
		this.addParam("mailUserInfo", mailUserInfo);
		this.testResonse(this.createApiRequest());
	}

	@Test
	// 修改邮箱状态
	public void testUpdateMailUserState() {
		this.setUp(this.getUrl("/email/updateMailUserState"));
		this.addParam("emailAddress", "243860327@qq.com");
		this.addParam("state", "DELETED");
		this.addParam("id", (long) 721);
		this.testResonse(this.createApiRequest());
	}

	@Test
	public void testDelMailUser() { // 删除邮箱
		this.setUp(this.getUrl("/email/delMailUser"));
		this.addParam("id", "721");
		this.testResonse(this.createApiRequest());
	}

	/*@Test
	public void testSelectMailUser() { // 获取全部邮箱
		this.setUp(this.getUrl("/email/selectMailUser"));
		this.addParam("emailAddress", "");
		this.addParam("iDisplayStart", 0);
		this.addParam("iDisplayLength", 20);
		this.addParam("sortColumns", "classify");
		this.addParam("sSortDir_0", "desc");
		this.testResonse(this.createApiRequest());
	}*/

	@Test
	public void testSelectMailUserByMailAddress() { // 通过邮箱地址获取
		this.setUp(this.getUrl("/email/selectMailUserByMailAddress"));
		this.addParam("emailAddress", "777777777@qq.com");
		this.addParam("isV2", true);
		this.testResonse(this.createApiRequest());
	}

	
	@Test
	public void testSelectMailUserStatus() { // 获取全部邮箱  status    同邮箱地址获取
		this.setUp(this.getUrl("/email/selectMailUser/status"));
		this.addParam("emailAddress", "r");
		//this.addParam("userStatus", "1");   //1 adapter  
		this.addParam("iDisplayStart", 0);
		this.addParam("iDisplayLength", 5);
		this.addParam("sortColumns", "classify");
		this.addParam("sSortDir_0", "desc");
		this.testResonse(this.createApiRequest());
	}
	
	
	@Test
	public void testSelectMailTask() { // 获取全部task
		this.setUp(this.getUrl("/email/select/EmailTask"));
		this.addParam("iDisplayStart", 0);
		this.addParam("iDisplayLength", 20);
		this.addParam("status", "READY");
		this.addParam("sortColumns", "status");
		this.addParam("sSortDir_0", "desc");
		this.testResonse(this.createApiRequest());
	}
	
	@Test
	public void testMethodInsert() { // 测试存数据
		this.setUp(this.getUrl("/email/filePath/Insert"));
		this.addParam("title", "找回密码");
		this.addParam("toMail", new String[] { "243860327@qq.com","kaige8312@163.com" });
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		this.addParam(
				"mailContent",
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
						+ "<HTML>"
						+ "<HEAD><TITLE>会员密码找回</TITLE></HEAD>"
						+ "<BODY>"
						+ "<p align='left'>亲爱的用户:</p>"
						+ "<p align='left'>&nbsp;&nbsp;&nbsp;&nbsp;请点击冒号后面的链接;如果不能点击,请将冒号后面的链接复制并粘帖到浏览器的地址输入框,然后敲回车即可。（链接有效时间为1个小时）：</p>"
						+ "<p align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://sempro-qa.pzoom.com/pzoom_forgetpassword.html</p>"
						+ "<br/><br/><p align='left'>品种互动团队</p> <p align='left'>"
						+ sdf.format(Calendar.getInstance().getTime()) + "</p>"
						+ "</BODY></HTML>");
		this.addParam("mailType", "HTML");
		this.addParam("classify", "7");
		this.addParam("planTime",  new Date().getTime()+"");
		this.testResonse(this.createApiRequest());
	}
	
	@Test
	public void testDelMailUser11() { // 删除邮箱
      String str="[243860327@qq.com, kaige8312@163.com]";
     // String abc="[243860327@qq.com, kaige8312@163.com]";
  	Gson gson = new Gson();
  	List<String> ps =
  			 gson.fromJson(str, new TypeToken<List<String>>(){}.getType());
  	 System.out.println(ps.size());
      
	}
	
	
	@Test
	public void testSelectProduct() { // 获取全部task
		this.setUp(this.getUrl("/email/select/product"));
		this.addParam("productType", "ACTIVE");
		this.addParam("iDisplayStart", 0);
		this.addParam("iDisplayLength", 20);
		this.testResonse(this.createApiRequest());
	}
	
	@Test
	public void testSelectNews() { // 获取全部task
		this.setUp(this.getUrl("/email/select/news"));
		this.addParam("newsType", "COMPANY");
		this.addParam("iDisplayStart", 0);
		this.addParam("iDisplayLength", 20);
		this.testResonse(this.createApiRequest());
	}
}
