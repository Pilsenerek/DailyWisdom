package pl.pils.dw.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Formula;


@Entity
@NamedEntityGraph(
		name = "DailyWisdom",
		attributeNodes = {
				@NamedAttributeNode(value = "votes"),
				@NamedAttributeNode(value = "author"),
				@NamedAttributeNode(value = "category")
		})
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
	
    @OneToMany(mappedBy = "dailyWisdom", fetch = FetchType.EAGER)
    private List<DailyWisdomVote> votes = Collections.emptyList();
    
    /*
     * Sorting by collection.size
     * This native query fragment should be compatible with named/generated main query
     */
    @Formula("(SELECT COUNT(*) FROM daily_wisdom_vote WHERE daily_wisdom_vote.daily_wisdom_id = dailywisdo0_.id)")
    private int votesNumber = 0;
    
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

	public int getVotesNumber() {
		
		return this.votesNumber;
	}

	
}
