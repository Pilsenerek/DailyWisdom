package pl.pils.dw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pils.dw.entity.DailyWisdom;
import pl.pils.dw.entity.DailyWisdomVote;
import pl.pils.dw.entity.User;

public interface DailyWisdomVoteRepository extends JpaRepository<DailyWisdomVote, Long> {


	public DailyWisdomVote findOneByUserAndDailyWisdom(User user, DailyWisdom dailyWisdom);
	
}
