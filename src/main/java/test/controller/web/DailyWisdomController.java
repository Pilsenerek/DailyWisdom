package test.controller.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import test.entity.DailyWisdom;
import test.service.DailyWisdomService;

@Controller
public class DailyWisdomController {

	@Autowired
	private DailyWisdomService dailyWisdomService;
	
	
	@RequestMapping("/dw")
	public String list(Map<String, Object> model, Pageable pageable){
		Page<DailyWisdom> dailyWisdoms = this.dailyWisdomService.getDailyWisdoms(pageable);
		//model.put("dailyWisdoms", dailyWisdoms);
		model.put("page", dailyWisdoms);
		model.put("persons", dailyWisdoms);
		//dailyWisdoms.
		
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
