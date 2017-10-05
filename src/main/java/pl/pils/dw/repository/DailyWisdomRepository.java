package pl.pils.dw.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pils.dw.entity.DailyWisdom;

public interface DailyWisdomRepository extends JpaRepository<DailyWisdom, Long> {

	
	public List<DailyWisdom> findByAuthorId(Long authorId);
	
	@Query("SELECT a FROM DailyWisdom a ORDER by RAND()")
    public List<DailyWisdom> findAllOrderByRand(Pageable pageable);
	
	public DailyWisdom findOneBySlug(String slug);
	
	@EntityGraph("DailyWisdom")
	public Page<DailyWisdom> findByJokeContaining(String joke, Pageable pageable);
	
	@EntityGraph("DailyWisdom")
	public Page<DailyWisdom> findAll(Pageable pageable);
	
	//Example named query
	@Query("SELECT dailywisdo0_ FROM DailyWisdom dailywisdo0_ LEFT JOIN dailywisdo0_.votes WHERE dailywisdo0_.joke LIKE %:joke% GROUP BY dailywisdo0_.id")
	@EntityGraph("DailyWisdom")
	public Page<DailyWisdom> findAllByJokeParam(@Param("joke") String joke, Pageable pageable);
}
