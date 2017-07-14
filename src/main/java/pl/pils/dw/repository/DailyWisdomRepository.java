package pl.pils.dw.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.pils.dw.entity.DailyWisdom;

public interface DailyWisdomRepository extends JpaRepository<DailyWisdom, Long> {

	
	public List<DailyWisdom> findByAuthorId(Long authorId);
	
	@Query("SELECT a FROM DailyWisdom a ORDER by RAND()")
    public List<DailyWisdom> findAllOrderByRand(Pageable pageable);
	
	public DailyWisdom findOneBySlug(String slug);
	
	
}
