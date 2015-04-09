package com.pzoom.ads.test;

import java.io.ByteArrayOutputStream;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.pzoom.ads.platform.backend.annotation.ApiPath;
import com.pzoom.ads.platform.backend.core.webapi.ApiMaps;
import com.pzoom.ads.platform.backend.core.webapi.ApiMaps.ApiMethod;
import com.pzoom.ads.platform.backend.core.webapi.ApiRequest;
import com.pzoom.ads.platform.backend.core.webapi.ApiResponse;
import com.pzoom.ads.platform.backend.core.webapi.ApiResult;
import com.pzoom.ads.platform.backend.core.webapi.ApiService;
import com.pzoom.ads.platform.backend.core.webapi.ApiUser;
import com.pzoom.ads.platform.backend.core.webapi.json.gson.GsonHelper;
import com.pzoom.ads.platform.backend.profile.AdNetwork;
import com.pzoom.ads.platform.backend.profile.DataFormat;
import com.pzoom.ads.platform.backend.util.Paging;
import com.pzoom.ads.platform.backend.util.StringUtils;

public abstract class WebSendMailTest<T extends ApiService> {

	private String contextPath = "/backend";
	protected ApiMethod apiMethod = null;

	private ApiUser apiUser = null;

	protected DataFormat apiDataType = null;
	private Class<T> tOfClasss = null;

	protected Map<String, Object> parameters;

	@SuppressWarnings("unchecked")
	protected WebSendMailTest() {
		this.tOfClasss =
				(Class<T>) ((java.lang.reflect.ParameterizedType) this.getClass().getGenericSuperclass())
						.getActualTypeArguments()[0];
	}

	public void setUp(String requestURI) {
		this.apiUser = new ApiUser("1"); // create user instance
		ApiMaps.Register(this.tOfClasss);
		String ext = requestURI.substring(requestURI.lastIndexOf(".") + 1);
		apiDataType = DataFormat.valueOf(ext.toUpperCase());
		String apiAction = requestURI.substring(contextPath.length(), requestURI.lastIndexOf("."));
		apiMethod = ApiMaps.findApiMethod(apiAction);
		this.parameters = new HashMap<String, Object>();
		this.parameters.put("locale", "zh_CN");
		this.parameters.put("network", AdNetwork.GOOGLE);
		this.parameters.put("params", new HashMap<String, Object>());
	}

	/**
	 * addParam(Here describes this method function with a few words)
	 * 
	 * add paramter
	 * 
	 * @param keyName
	 * @param value
	 * 
	 *            void
	 */
	@SuppressWarnings("unchecked")
	protected void addParam(String keyName, Object value) {
		if (null != this.parameters) {
			if (this.parameters.containsKey("params")) {
				Object mapOfObject = this.parameters.get("params");
				if (mapOfObject instanceof Map) {
					Map<String, Object> paramOfMap = (Map<String, Object>) mapOfObject;
					paramOfMap.put(keyName, value);
					this.parameters.put("params", paramOfMap);
				}
			}
		}
	}

	protected void testResonse(ApiRequest request) {
		if (null == request)
			throw new IllegalArgumentException("request is null");
		if (null != this.apiMethod) {
			try {
				ApiResult result = (ApiResult) this.apiMethod.getMethod().invoke(null, request, this.apiUser);
				ApiResponse response = ApiResponse.getInstance(this.apiDataType, result);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				response.write(outputStream, "UTF-8");
				String responseOfString = new String(outputStream.toByteArray(), "UTF-8");
				// log.debug(responseOfString);
				System.out.println(responseOfString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected ApiRequest createApiRequest() {
		if (null == this.parameters)
			throw new IllegalArgumentException("the paramters is null");
		String json = GsonHelper.buildGson().toJson(this.parameters);
		System.out.println(json);
		ApiPath path = new ApiPath() {

			public Class<? extends Annotation> annotationType() {
				return ApiPath.class;
			}

			public String value() {
				return "";
			}
		};
		ApiRequest request = ApiRequest.getInstance(this.apiDataType, json);
		return request;
	}

	protected String getUrl(String url) {
		if (!StringUtils.hasLength(url))
			throw new IllegalArgumentException("url is blank");
		return this.contextPath + url + ".json";
	}

	protected Paging getPager() {
		Paging pager = new Paging();
		pager.setPageSize(10);
		return pager;
	}

}
