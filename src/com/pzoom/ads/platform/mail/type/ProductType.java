package com.pzoom.ads.platform.mail.type;


public enum ProductType {
	ACTIVE,PREPARING,SOLDOUT;
	public static ProductType get(String val) {
		for (ProductType item : ProductType.values()) {
			if (item.toString().equalsIgnoreCase(val)) {
				return item;
			}
		}
		return null;
	}
}
