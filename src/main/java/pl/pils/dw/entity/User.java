package pl.pils.dw.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	@OneToMany(mappedBy="author")
	@JsonIgnore
	private Set<DailyWisdom> dailyWisdoms = new HashSet<DailyWisdom>();
	
	public User() {
		
	}
	
	public User(String email, String firstName, String lastName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName(){
		
		return this.firstName.concat(" ").concat(this.lastName);
	}
	
	

}
