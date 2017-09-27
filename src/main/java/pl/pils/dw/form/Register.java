package pl.pils.dw.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;;

public class Register {
	
	@Size(min=5, max=99)
	@NotNull
	@Email
	private String email;
	
	@Size(min=3, max=20)
	private String firstName;
	
	@Size(min=3, max=40)
	private String lastName;
	
	@Size(min=3, max=99)
	@NotNull
	private String pass;
	
	@Size(min=3, max=99)
	@NotNull(message="Password confirmation has to be the same")
	private String confirmPass;
	
	private void checkPassword() {
		if (this.pass == null || this.confirmPass == null) {
			return;
		} else if (!this.pass.equals(confirmPass)) {
			this.confirmPass = null;
		}
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
		this.checkPassword();
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
		this.checkPassword();
	}
	
}
