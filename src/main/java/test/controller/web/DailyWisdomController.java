package test.controller.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<DailyWisdom> getDailyWisdoms(){
		
		return this.dailyWisdomService.getDailyWisdoms();
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
