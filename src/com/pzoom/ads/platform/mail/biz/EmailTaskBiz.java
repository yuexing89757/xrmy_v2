package com.pzoom.ads.platform.mail.biz;

import java.util.List;

import com.pzoom.ads.platform.backend.core.business.Result;
import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.mail.type.StatusType;
import com.pzoom.ads.platform.mail.view.EmailTaskView;

public interface EmailTaskBiz {
    public Result<Boolean> addEmailTask(String[] toMail, String title, String mailConent,
			String[] attachPath, String[] attachDescription,
			String[] attachName, String[] Cc, String[] Bcc,
			String mailType, String classify, String planTime);
    public List<EmailTaskView> getEmailTask(StatusType status,String sortColunn,String sortDir,Paging paging);
}
