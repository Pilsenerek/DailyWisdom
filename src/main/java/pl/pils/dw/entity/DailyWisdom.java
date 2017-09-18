package pl.pils.dw.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DailyWisdom {
	
	@Id
	@GeneratedValue
	private Long id;
	private String joke;
	@Column(unique = true)
	private String slug;
	@ManyToOne
	private User author;
	@ManyToOne
	private Category category;
	
    @OneToMany(mappedBy = "dailyWisdom", fetch = FetchType.LAZY)
    private List<DailyWisdomVote> votes = Collections.emptyList();
    
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public DailyWisdom(){
		
	}
	
	public DailyWisdom(String joke, String slug, User author, Category category) {
		super();
		this.joke = joke;
		this.slug = slug;
		this.author = author;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}
	public String getJoke() {
		return joke;
	}
	public void setJoke(String joke) {
		this.joke = joke;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<DailyWisdomVote> getVotes() {
		return votes;
	}

	public void setVotes(List<DailyWisdomVote> votes) {
		this.votes = votes;
	}
	
	public DailyWisdomVote getVoteByUser(User user){
		
		return this.votes.get(0);
	}

	
}
