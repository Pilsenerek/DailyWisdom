package test.controller.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.entity.DailyWisdom;
import test.service.DailyWisdomService;

@Controller
public class IndexController {
	
	@Autowired
	private DailyWisdomService dailyWisdomService;
	
	@RequestMapping("/")
	public String index(Map<String, Object> model){
		List<DailyWisdom> sentences = this.dailyWisdomService.findOneOrderByRand();
		DailyWisdom sentence = sentences.get(0);
		model.put("sentence", sentence);
		
		return "index";
	}
	
	

}
