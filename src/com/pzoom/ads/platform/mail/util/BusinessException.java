package com.pzoom.ads.platform.mail.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pzoom.ads.platform.backend.core.UncheckedException;
import com.pzoom.ads.platform.backend.util.ObjectUtils;
import com.pzoom.ads.platform.backend.util.StringUtils;

public class BusinessException extends UncheckedException {

	private static final long serialVersionUID = 1L;

	public Map<String, String> errorMap = new HashMap<String, String>();

	@Override
	public String getMessage() {
		return super.getMessage();
	} 

	public void put(String key, String errorField) {
		if (!errorMap.containsKey(key)) {
			errorMap.put(key, "");
		}
		if (StringUtils.hasLength(errorField)) {
			String value = errorMap.get(key);
			String[] valueArray = value.split(StringUtils.COMMA);
			if (valueArray.length == 2&&!valueArray[1].endsWith("...")) {
				errorMap.put(key, value + "...");
				return;
			}
			if (valueArray.length >= 2) {
				return;
			}
			
			if (!Arrays.asList(valueArray).contains(errorField)) {
				StringBuffer valueBuffer = new StringBuffer(value);
				ModelUtils modelUtils = new ModelUtils();
				modelUtils.append(valueBuffer, errorField);
				errorMap.put(key, valueBuffer.toString());
			}
		}
	}
	
	
	
	public void putAll(String[] keys,String errorField)
	{
		if(!ObjectUtils.isEmpty(keys))
		{
			for (String key : keys) {
				put(key, errorField);
			}
		}
	}

	public boolean containKeys(String... keys) {
		Set<String> errorKeys = this.errorMap.keySet();
		if (ObjectUtils.isEmpty(errorKeys))
			return true;
		else {
			List<String> paramKeys = new ArrayList<String>();
			Collections.addAll(paramKeys, keys);
			if (paramKeys.containsAll(errorKeys)) {
				return true;
			}
			return false;
		}
	}

	public boolean isThrow() {
		return this.errorMap.size() > 0;
	}

	public Map<String, String> getErrorMap() {
		return errorMap;
	}
	
	
	public Set<String> getErrorKey()
	{
		return this.errorMap.keySet();
	}

}
