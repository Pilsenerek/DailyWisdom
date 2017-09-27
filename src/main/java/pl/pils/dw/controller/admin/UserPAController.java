package pl.pils.dw.controller.admin;

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
import pl.pils.dw.form.ProfileForm;
import pl.pils.dw.repository.UserRepository;

@Controller
@RequestMapping("/pa/user")
public class UserPAController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
	public String edit(Principal principal, ProfileForm profileForm, Map<String, Object> modell) {
		User user = this.userRepository.findOneByEmail(principal.getName());
		profileForm.setFirstName(user.getFirstName());
		profileForm.setLastName(user.getLastName());
		
		return "user/edit";
	}
		
	@RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
	public String edit(Principal principal, @Valid ProfileForm profileForm, BindingResult bindingResult, Map<String, Object> model, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			
			return "user/edit";
		}
		User user = this.userRepository.findOneByEmail(principal.getName());
		user.setFirstName(profileForm.getFirstName());
		user.setLastName(profileForm.getLastName());
		if(profileForm.getPass() != null){
			user.setPass(this.passwordEncoder.encode(profileForm.getPass()));
		}
		this.userRepository.save(user);
		attributes.addFlashAttribute("msg", "Your profile has just been updated.");
		
		return "redirect:/user/profile";
	}
	
	
}
