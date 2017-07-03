package test.controller.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.entity.DailyWisdom;
import test.repository.DailyWisdomRepository;

@Controller
public class IndexController {
	
	@Autowired
	private DailyWisdomRepository dailyWisdomRepository;
	
	@RequestMapping("/")
	public String index(Map<String, Object> model){
		Pageable one = new PageRequest(0, 1);
		List<DailyWisdom> sentences = this.dailyWisdomRepository.findAllOrderByRand(one);
		DailyWisdom sentence = sentences.get(0);
		model.put("sentence", sentence);
		
		return "index";
	}
	
	

}
