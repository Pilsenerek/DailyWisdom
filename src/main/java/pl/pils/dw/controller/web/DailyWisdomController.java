package pl.pils.dw.controller.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pils.dw.dto.SortView;
import pl.pils.dw.entity.DailyWisdom;
import pl.pils.dw.service.DailyWisdomService;
import pl.pils.dw.service.SortUrlService;

@Controller
public class DailyWisdomController {
	
	@Autowired
	private DailyWisdomService dailyWisdomService;
	@Autowired
	private SortUrlService sortUrlService;
	
	@RequestMapping("/dw")
	public String list(Map<String, Object> model, Pageable pageable){
		//@todo separate sort definitions
		Map<String, String> sortKeys = new LinkedHashMap<String, String>();
		sortKeys.put("id", "id"); //first = default sort key
		sortKeys.put("text", "joke");
		sortKeys.put("category", "category.name");
		sortKeys.put("author", "author.lastName");
		
		SortView sortView = this.sortUrlService.getSortView(sortKeys);
		final PageRequest page = new PageRequest(
				  pageable.getPageNumber(),
				  pageable.getPageSize(),
				  Direction.fromString(sortView.getOrder()),
				  sortView.getField()
				);
		
		Page<DailyWisdom> dailyWisdoms = this.dailyWisdomService.getDailyWisdoms(page);
		
		model.put("page", dailyWisdoms);
		model.put("persons", dailyWisdoms);
		model.put("sortUrls", sortView.getSortUrls());
		model.put("order", sortView.getOrder());
		model.put("sort", sortView.getKey());
		
		return "dw/list";
	}
		
	@RequestMapping("/dw/{id:[\\d]+}")
	public String getDailyWisdomById(@PathVariable Long id, Map<String, Object> model){
		DailyWisdom dailyWisdom = this.dailyWisdomService.getDailyWisdom(id);
		model.put("sentence", dailyWisdom);
		
		return "dw/index";
	}
	
	@RequestMapping("/dw/{slug:[a-z0-9-]*}")
	public String getDailyWisdomBySlug(@PathVariable String slug, Map<String, Object> model){
		DailyWisdom dailyWisdom = this.dailyWisdomService.getDailyWisdomBySlug(slug);
		model.put("sentence", dailyWisdom);
		
		return "dw/index";
	}
	
	@RequestMapping("/dw/user/{id}")
	public List<DailyWisdom> getDailyWisdomByAuthor(@PathVariable Long id){
		
		return this.dailyWisdomService.getDailyWisdomByUserId(id);
	}
	
}
