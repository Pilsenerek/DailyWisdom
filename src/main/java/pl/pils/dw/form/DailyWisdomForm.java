package pl.pils.dw.form;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pl.pils.dw.entity.Category;

public class DailyWisdomForm {
	
	@NotNull
	@Size(min=3, max=255)
	private String joke;
	
	@NotNull
	@Valid
	private Category category;

	public String getJoke() {
		return joke;
	}

	public void setJoke(String joke) {
		this.joke = joke;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
