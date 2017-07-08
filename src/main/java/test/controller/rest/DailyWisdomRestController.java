package test.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.entity.DailyWisdom;
import test.service.DailyWisdomService;

@RestController
public class DailyWisdomRestController {

	@Autowired
	private DailyWisdomService dailyWisdomService;
	
	
	@RequestMapping("/cnj")
	public Page<DailyWisdom> getDailyWisdoms(Pageable pageable){
		
		return this.dailyWisdomService.getDailyWisdoms(pageable);
	}
		
	@RequestMapping("/cnj/{id}")
	public DailyWisdom getDailyWisdom(@PathVariable Long id){
		
		return this.dailyWisdomService.getDailyWisdom(id);
	}
	
	@RequestMapping("/cnj/user/{id}")
	public List<DailyWisdom> getDailyWisdomByAuthor(@PathVariable Long id){
		
		return this.dailyWisdomService.getDailyWisdomByUserId(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cnj")
	public void addDailyWisdom(@RequestBody DailyWisdom dailyWisdom){
		
		this.dailyWisdomService.addDailyWisdom(dailyWisdom);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/cnj/{id}")
	public void updateDailyWisdom(@RequestBody DailyWisdom dailyWisdom, @PathVariable Long id){
		
		this.dailyWisdomService.updateDailyWisdom(id, dailyWisdom);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/cnj/{id}")
	public void deleteDailyWisdom(@PathVariable Long id){
		
		this.dailyWisdomService.deleteDailyWisdom(id);
	}
	
}
