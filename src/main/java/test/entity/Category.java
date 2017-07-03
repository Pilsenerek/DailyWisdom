package test.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String image;
	@OneToMany(mappedBy="category")
	@JsonIgnore
	private Set<DailyWisdom> dailyWisdoms = new HashSet<DailyWisdom>();
	
	public Category() {
		
	}

	public Category(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<DailyWisdom> getDailyWisdoms() {
		return dailyWisdoms;
	}
	
	public void setDailyWisdoms(Set<DailyWisdom> dailyWisdoms) {
		this.dailyWisdoms = dailyWisdoms;
	}
	
	public void addDailyWisdom(DailyWisdom dailyWisdom){
		this.dailyWisdoms.add(dailyWisdom);
	}
	
	public void removeDailyWisdom(DailyWisdom dailyWisdom){
		this.dailyWisdoms.remove(dailyWisdom);
	}
	

	

}
