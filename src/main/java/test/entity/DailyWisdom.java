package test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	
}
