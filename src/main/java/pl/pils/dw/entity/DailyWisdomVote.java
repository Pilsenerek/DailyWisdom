package pl.pils.dw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = { "user_id", "daily_wisdom_id"})} )
public class DailyWisdomVote {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private DailyWisdom dailyWisdom;

	public DailyWisdomVote(){
		
	}
	
	public DailyWisdomVote(User user, DailyWisdom dailyWisdom) {
		super();
		this.user = user;
		this.dailyWisdom = dailyWisdom;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DailyWisdom getDailyWisdom() {
		return dailyWisdom;
	}

	public void setDailyWisdom(DailyWisdom dailyWisdom) {
		this.dailyWisdom = dailyWisdom;
	}
	
	
	
}
