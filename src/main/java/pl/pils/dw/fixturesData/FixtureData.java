package pl.pils.dw.fixturesData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;

import pl.pils.dw.entity.Category;
import pl.pils.dw.entity.DailyWisdom;
import pl.pils.dw.entity.User;
import pl.pils.dw.repository.CategoryRepository;
import pl.pils.dw.repository.DailyWisdomRepository;
import pl.pils.dw.repository.UserRepository;


@Component
public class FixtureData implements ApplicationRunner {

	private DailyWisdomRepository dailyWisdomRepository;
	private UserRepository userRepository;
	private CategoryRepository categoryRepository;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;

	@Autowired
	public FixtureData(DailyWisdomRepository chuckNorrisRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
		this.dailyWisdomRepository = chuckNorrisRepository;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
	}

	public void run(ApplicationArguments args) throws Exception {
		ArrayList<String> ddlAuto = new ArrayList<String>(Arrays.asList("create", "create-drop"));
		if (ddlAuto.contains(this.ddlAuto)) {
			Faker faker = new Faker(new Locale("pl"));
			//Faker faker = new Faker();
			Slugify slg = new Slugify();
			
			//categories entity
			ArrayList<Category> categories = new ArrayList<Category>();
			categories.add(new Category("Chuck Norris", "chuck.jpg"));
			categories.add(new Category("Yoda Master", "yoda.png"));
			categories.add(new Category("Other", "other.png"));
			for(Category category : categories){
				this.categoryRepository.save(category);
			}
			
			//Users entity
			ArrayList<User> users = new ArrayList<User>();
			for (int i = 1; i < 9; i++) {
				String email = faker.internet().emailAddress();
				String firstName = faker.name().firstName();
				String lastName = faker.name().lastName();
				User user = new User(email, firstName, lastName);
				this.userRepository.save(user);
				users.add(user);
			}
			
			//ChuckNorris
			Set<String> cns = new HashSet<String>(); //to avoid duplicates
			for (int i = 1; i < 99; i++) {
				String joke = faker.chuckNorris().fact();
				cns.add(joke);
			}
			for(String joke : cns){
				String slug = slg.slugify(joke);
				Random random = new Random(); 
				int randomIndex = random.nextInt(users.size());
				DailyWisdom cn = new DailyWisdom(joke, slug, users.get(randomIndex), categories.get(0));
				this.dailyWisdomRepository.save(cn);
			}
			
			//Yoda (faker doesn't support it yet)
			ArrayList<String> yodas = new ArrayList<String>(Arrays.asList(
					"Already know you that which you need",
					"Adventure. Excitement. A Jedi craves not these things.",
					"Not if anything to say about it I have.",
					"Size matters not. Look at me. Judge me by my size, do you? Hmm? Hmm. And well you should not.",
					"The dark side clouds everything. Impossible to see the future is.",
					"Clear your mind must be, if you are to find the villains behind this plot.",
					"Always two there are, no more, no less. A master and an apprentice.",
					"Do. Or do not. There is no try.",
					"Much to learn you still have…my old padawan. This is just the beginning!",
					"Good relations with the Wookiees, I have.",
					
					"Truly wonderful, the mind of a child is.",
					"Always pass on what you have learned.",
					"Once you start down the dark path, forever will it dominate your destiny, consume you it will.",
					"In the end, cowards are those who follow the dark side.",
					"Mind what you have learned. Save you it can.",
					"Your weapons, you will not need them.",
					"To answer power with power, the Jedi way this is not.",
					"In this war, a danger there is, of losing who we are.",
					"You will find only what you bring in.",
					"Around the survivors a perimeter create.",
					
					"Attachment leads to jealously. The shadow of greed, that is.",
					"Stay for some soup you must.",
					"Soon will I rest, yes, forever sleep. Earned it I have. Twilight is upon me, soon night must fall.",
					"At an end your rule is, and not short enough it was!"
			));
			for(String yoda : yodas){
				String slug = slg.slugify(yoda);
				Random random = new Random(); 
				int randomIndex = random.nextInt(users.size());
				DailyWisdom cn = new DailyWisdom(yoda, slug, users.get(randomIndex), categories.get(1));
				this.dailyWisdomRepository.save(cn);
			}
			
			
			
		}
	}
	
	
}
