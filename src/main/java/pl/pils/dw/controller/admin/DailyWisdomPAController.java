package pl.pils.dw.controller.admin;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.github.slugify.Slugify;
import pl.pils.dw.entity.Category;
import pl.pils.dw.entity.DailyWisdom;
import pl.pils.dw.entity.User;
import pl.pils.dw.form.DailyWisdomForm;
import pl.pils.dw.repository.CategoryRepository;
import pl.pils.dw.repository.DailyWisdomRepository;
import pl.pils.dw.repository.UserRepository;
import pl.pils.dw.service.DailyWisdomService;

@Controller
public class DailyWisdomPAController {
	
	@Autowired
	private DailyWisdomRepository dailyWisdomRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private DailyWisdomService dailyWisdomService;
	
	@RequestMapping(value = "/pa/dw/add", method = RequestMethod.GET)
	public String add(DailyWisdomForm dailyWisdomForm, Map<String, Object> model) {
		List<Category> categories = (List<Category>) this.categoryRepository.findAll();
		model.put("categories", categories);
		
		return "dw/add";
	}
	
	@RequestMapping(value = "/pa/dw/add", method = RequestMethod.POST)
	public String add(Principal principal, @Valid DailyWisdomForm dailyWisdomForm, BindingResult bindingResult, Map<String, Object> model, RedirectAttributes attributes) {
		List<Category> categories = (List<Category>) this.categoryRepository.findAll();
		model.put("categories", categories);
		if (bindingResult.hasErrors()) {
			
			return "dw/add";
		}
		User user = this.userRepository.findOneByEmail(principal.getName());
		Slugify slg = new Slugify();
		DailyWisdom newDailyWisdom = new DailyWisdom(dailyWisdomForm.getJoke(), slg.slugify(dailyWisdomForm.getJoke()), user, dailyWisdomForm.getCategory());
		this.dailyWisdomRepository.save(newDailyWisdom);
		attributes.addFlashAttribute("msg", "Your sentence has just been created.");
		
		return "redirect:/dw";
	}
	
	@RequestMapping(value = "/pa/dw/{id:[\\d]+}/edit", method = RequestMethod.GET)
	public String edit(DailyWisdomForm dailyWisdomForm, @PathVariable Long id, Map<String, Object> model){
		List<Category> categories = (List<Category>) this.categoryRepository.findAll();
		model.put("categories", categories);
		DailyWisdom dailyWisdom = this.dailyWisdomService.getDailyWisdom(id);
		dailyWisdomForm.setJoke(dailyWisdom.getJoke());
		dailyWisdomForm.setCategory(dailyWisdom.getCategory());
		model.put("dailyWisdom", dailyWisdom);
		
		return "dw/edit";
	}
	
	@RequestMapping(value = "/pa/dw/{id:[\\d]+}/edit", method = RequestMethod.POST)
	public String edit(@Valid DailyWisdomForm dailyWisdomForm, BindingResult bindingResult, @PathVariable Long id, Map<String, Object> model, RedirectAttributes attributes) {
		List<Category> categories = (List<Category>) this.categoryRepository.findAll();
		model.put("categories", categories);
		DailyWisdom dailyWisdom = this.dailyWisdomService.getDailyWisdom(id);
		model.put("dailyWisdom", dailyWisdom);
		if (bindingResult.hasErrors()) {
			
			return "dw/edit";
		}
		Slugify slg = new Slugify();
		dailyWisdom.setJoke(dailyWisdomForm.getJoke());
		dailyWisdom.setSlug(slg.slugify(dailyWisdomForm.getJoke()));
		dailyWisdom.setCategory(dailyWisdomForm.getCategory());
		this.dailyWisdomRepository.save(dailyWisdom);
		attributes.addFlashAttribute("msg", "Your sentence has just been saved.");
		
		return "redirect:/dw";
	}
	
	@RequestMapping(value = "/pa/dw/{id:[\\d]+}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable Long id, Map<String, Object> model, RedirectAttributes attributes){
		DailyWisdom dailyWisdom = this.dailyWisdomService.getDailyWisdom(id);
		this.dailyWisdomRepository.delete(dailyWisdom);
		attributes.addFlashAttribute("msg", "Your sentence has just been deleted.");
		
		return "redirect:/dw";
	}
	
}
