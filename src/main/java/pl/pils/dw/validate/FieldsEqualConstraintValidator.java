package pl.pils.dw.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.pils.dw.form.ProfileForm;
import java.lang.reflect.*;

public class FieldsEqualConstraintValidator implements ConstraintValidator<FieldsEqualConstraint, Object> {
	
	private String firstGetter;
	
	private String secondGetter;
	
	@Override
	public void initialize(FieldsEqualConstraint fieldsEqualConstraint) {
		String first = fieldsEqualConstraint.firstField();
		first = Character.toUpperCase(first.charAt(0)) + first.substring(1);
		this.firstGetter = "get" + first;
		
		String second = fieldsEqualConstraint.secondField();
		second = Character.toUpperCase(second.charAt(0)) + second.substring(1);
		this.secondGetter = "get" + second;
	}

	@Override
	public boolean isValid(Object candidate, ConstraintValidatorContext context) {
		ProfileForm user = (ProfileForm) candidate;
		try {
			Method method = user.getClass().getMethod(this.firstGetter);
			Method method2 = user.getClass().getMethod(this.secondGetter);
			try {
				String pass = (String) method.invoke(user);
				String confirmation = (String) method2.invoke(user);
				
				System.out.println(pass);
				System.out.println(confirmation);
				if (pass == null && confirmation == null) {

					return true;
				}
				if (pass == null || confirmation == null) {

					return false;
				}
			    
			    return user.getPass().equals(user.getConfirmPass());
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
