package pl.pils.dw.controller.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		Page<DailyWisdom> dailyWisdoms = this.dailyWisdomService.getDailyWisdoms(pageable);
		ArrayList<String> sortKeys = new ArrayList<String>(Arrays.asList("id", "text", "category", "author"));
		SortView sortView = this.sortUrlService.getSortView(sortKeys);
		
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
