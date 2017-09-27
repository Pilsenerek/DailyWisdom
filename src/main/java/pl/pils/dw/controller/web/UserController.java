package pl.pils.dw.controller.web;

import java.security.Principal;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.pils.dw.entity.User;
import pl.pils.dw.form.RegisterForm;
import pl.pils.dw.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/profile")
	public String profile(Principal principal, Map<String, Object> model){
		User user = this.userRepository.findOneByEmail(principal.getName());
		model.put("user", user);
		
		return "user/profile";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(RegisterForm register, Map<String, Object> modell) {
		
		return "user/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid RegisterForm registerForm, BindingResult bindingResult, Map<String, Object> model, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			System.out.println("qwerty " + registerForm.getFirstName());
			
			return "user/register";
		}
		User newUser = new User(registerForm.getEmail(), registerForm.getFirstName(), registerForm.getLastName(), this.passwordEncoder.encode(registerForm.getPass())); 
		this.userRepository.save(newUser);
		attributes.addFlashAttribute("msg", "Your account has just been created, try to sign in");
		
		return "redirect:/login";
	}
		

	
}
