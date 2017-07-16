package pl.pils.dw.dto;

import java.util.Map;

public class SortView {

	private Map<String, String> sortUrls;
	
	private String order;
	
	private String key;
	
	private String field;

	public SortView(Map<String, String> sortUrls, String order, String key, String field) {
		super();
		this.sortUrls = sortUrls;
		this.order = order;
		this.key = key;
		this.field = field;
	}
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}

	public Map<String, String> getSortUrls() {
		return sortUrls;
	}

	public void setSortUrls(Map<String, String> sortUrls) {
		this.sortUrls = sortUrls;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
