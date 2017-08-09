package pl.pils.dw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.pils.dw.dto.DailyWisdomSearch;
import pl.pils.dw.entity.DailyWisdom;
import pl.pils.dw.repository.DailyWisdomRepository;

@Service
public class DailyWisdomService {
	
	@Autowired
	private DailyWisdomRepository dailyWisdomRepository;
	
	public Page<DailyWisdom> getDailyWisdoms(Pageable pageable){
		Page<DailyWisdom> sentences =  this.dailyWisdomRepository.findAll(pageable);
		
		return sentences;
	}
	
	public Page<DailyWisdom> getDailyWisdoms(Pageable pageable, DailyWisdomSearch search){
		if (search.getSearch() == null) {

			return this.dailyWisdomRepository.findAll(pageable);

		} else {

			return this.dailyWisdomRepository.findByJokeContaining(search.getSearch(), pageable);
		}
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
		List<DailyWisdom> sentences =  (List<DailyWisdom>)this.dailyWisdomRepository.findByAuthorId(id);
		
		return sentences;
	}
	
	public DailyWisdom getDailyWisdomBySlug(String slug) {
		DailyWisdom sentence =  this.dailyWisdomRepository.findOneBySlug(slug);
		
		return sentence;
	}
	
	public List<DailyWisdom> findOneOrderByRand(){
		Pageable one = new PageRequest(0, 1);
		List<DailyWisdom> sentences = this.dailyWisdomRepository.findAllOrderByRand(one);
		
		return sentences;
	}
	

}
