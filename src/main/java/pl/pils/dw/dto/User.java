package pl.pils.dw.dto;

import java.util.ArrayList;
import java.util.List;

public class User {

	public long id;
	public String email;
	public String firstName;
	public String lastName;
	public List<DailyWisdom> dailyWisdoms = new ArrayList<DailyWisdom>();

}
