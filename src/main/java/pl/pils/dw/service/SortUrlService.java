package pl.pils.dw.service;

import java.util.HashMap;
import java.util.List;
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
	
	public SortView getSortView(Map<String, String> sortKeys, List<String> keepKeys) {
		SortView sortView = new SortView(
				this.getSortUrls(sortKeys, keepKeys),
				this.getOrder(),
				this.getSort(sortKeys),
				this.getField(sortKeys)
		);
		
		return sortView;
	}
	
	private Map<String, String> getSortUrls(Map<String, String> sortKeys, List<String> keepKeys){
		Map<String, String> sortUrls = new HashMap<>();
		for(Map.Entry<String, String> entry : sortKeys.entrySet()){
			UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(this.getFullURL(this.httpServletRequest, false));
			uriComponentsBuilder
				.replaceQueryParam(SORT_NAME, entry.getKey())
				.replaceQueryParam(ORDER_NAME, this.getInverseOrder())
			;
			//add maintain params
			for (String keepKey : keepKeys) {
				String requestKeepValue = this.httpServletRequest.getParameter(keepKey);
				if (requestKeepValue != null) {
					uriComponentsBuilder.replaceQueryParam(keepKey, requestKeepValue);
				}
			}
			sortUrls.put(entry.getKey(), uriComponentsBuilder.toUriString());
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
	
	private String getSort(Map<String, String> sortKeys){
		String sort = this.httpServletRequest.getParameter(SORT_NAME);
		if(sort == null){
			sort = sortKeys.keySet().iterator().next();
		}
		
		return sort;
	}
	
	private String getField(Map<String, String> sortKeys){
		String sort = this.getSort(sortKeys);
		
		return sortKeys.get(sort);
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
	
	private String getFullURL(HttpServletRequest request, Boolean withQueryString) {
	    StringBuffer requestURL = request.getRequestURL();
	    String queryString = null;
	    if(withQueryString){
	    	queryString = request.getQueryString();
	    }
	    if (queryString == null) {
	    	
	        return requestURL.toString();
	    } else {
	    	
	        return requestURL.append('?').append(queryString).toString();
	    }
	}
}
