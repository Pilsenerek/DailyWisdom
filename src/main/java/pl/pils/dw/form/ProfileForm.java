package pl.pils.dw.form;

import javax.validation.constraints.Size;

import pl.pils.dw.validate.FieldsEqualConstraint;

@FieldsEqualConstraint(message = "Passwords are not equal", firstField = "pass", secondField = "confirmPass")
public class ProfileForm {
	
	@Size(min=3, max=20)
	private String firstName;
	
	@Size(min=3, max=40)
	private String lastName;
	
	@Size(min=3, max=99)
	private String pass;
	
	@Size(min=3, max=99)
	private String confirmPass;

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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
}
