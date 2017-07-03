package test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.entity.DailyWisdom;
import test.repository.DailyWisdomRepository;

@Service
public class DailyWisdomService {
	
	@Autowired
	private DailyWisdomRepository dailyWisdomRepository;
	
	public List<DailyWisdom> getDailyWisdoms(){
		List<DailyWisdom> sentences =  (List<DailyWisdom>)dailyWisdomRepository.findAll();
		
		return sentences;
	}
	
	public DailyWisdom getDailyWisdom(Long id){
		
		return this.dailyWisdomRepository.findOne(id);
	}
	
	public void addDailyWisdom(DailyWisdom dailyWisdom){
		
		this.dailyWisdomRepository.save(dailyWisdom);
	}

	public void updateDailyWisdom(Long id, DailyWisdom dailyWisdom) {
		
		this.dailyWisdomRepository.save(dailyWisdom);
		
	}

	public void deleteDailyWisdom(Long id) {
		
		this.dailyWisdomRepository.delete(id);
	}

	public List<DailyWisdom> getDailyWisdomByUserId(Long id) {
		List<DailyWisdom> sentences =  (List<DailyWisdom>)dailyWisdomRepository.findByAuthorId(id);
		
		return sentences;
	}
	
	public DailyWisdom getDailyWisdomBySlug(String slug) {
		DailyWisdom sentence =  dailyWisdomRepository.findOneBySlug(slug);
		
		return sentence;
	}
	

}
