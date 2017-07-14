package pl.pils.dw.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import pl.pils.dw.dto.SortView;

@Service
public class SortUrlService {

	public static final String SORT_NAME = "s";
	public static final String ORDER_NAME = "o";
	public static final String ORDER_DEFAULT = "ASC";
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	public SortView getSortView(ArrayList<String> sortKeys) {
		SortView sortView = new SortView(
				this.getSortUrls(sortKeys),
				this.getOrder(),
				this.getSort(sortKeys)
		);
		
		return sortView;
	}
	
	private Map<String, String> getSortUrls(ArrayList<String> sortKeys){
		Map<String, String> sortUrls = new HashMap<>();
		for(String key : sortKeys){
			UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(this.getFullURL(this.httpServletRequest));
			String url = uriComponentsBuilder.replaceQueryParam(SORT_NAME, key).replaceQueryParam(ORDER_NAME, this.getInverseOrder()).toUriString();
			sortUrls.put(key, url);
		}
		
		return sortUrls;
	}
	
	private String getOrder(){
		String order = this.httpServletRequest.getParameter(ORDER_NAME);
		if(order == null){
			order = ORDER_DEFAULT;
		}	
		
		return order;
	}
	
	private String getSort(ArrayList<String> sortKeys){
		String sort = this.httpServletRequest.getParameter(SORT_NAME);
		if(sort == null){
			sort = sortKeys.get(0);
		}
		
		return sort;
	}
	
	private String getInverseOrder(){
		String order = this.getOrder();
		if(order.equals("ASC")){
			order = "DESC";
		}else{
			order = "ASC";
		}
		
		return order;
	}
	
	private String getFullURL(HttpServletRequest request) {
	    StringBuffer requestURL = request.getRequestURL();
	    String queryString = request.getQueryString();
	    if (queryString == null) {
	    	
	        return requestURL.toString();
	    } else {
	    	
	        return requestURL.append('?').append(queryString).toString();
	    }
	}
}
