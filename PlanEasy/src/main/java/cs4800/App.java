package cs4800;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cs4800.calendar.Calendar;
import cs4800.dao.EventDAO;
import cs4800.dao.RoleDAO;
import cs4800.security.Role;
import cs4800.service.CalendarService;
import cs4800.service.UserService;
import cs4800.user.User;

@EnableMongoRepositories({"cs4800.dao"})
@SpringBootApplication
public class App extends SpringBootServletInitializer implements CommandLineRunner {
	
	@Bean
	CommandLineRunner init(RoleDAO roleRepository) {

	    return args -> {

	        Role adminRole = roleRepository.findByRole("ADMIN");
	        Role userRole = roleRepository.findByRole("USER");
	        if (adminRole == null) {
	            Role newAdminRole = new Role();
	            newAdminRole.setRole("ADMIN");
	            roleRepository.save(newAdminRole);
	        }
	        if (userRole == null) {
	        	Role newUserRole = new Role();
	        	newUserRole.setRole("USER");
	        	roleRepository.save(newUserRole);
	        }
	    };

	}

	private static final Logger log = Logger.getLogger(App.class.getName());

	@Autowired
	private EventDAO eventDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private CalendarService calendarService;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
    /**
     * This is the running main method for the web application.
     * Please note that Spring requires that there is one and
     * ONLY one main method in your whole program. You can create
     * other main methods for testing or debugging purposes, but
     * you cannot put extra main method when building your project.
     */
    public static void main(String[] args) throws Exception {
        // Run Spring Boot
        SpringApplication.run(App.class, args);
    }
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}
	
	/*
	 * Add some sample data into test collection
	 * ALSO sample of how to format input date and time parameters.
	 */
	@Override
	public void run(String... args) throws Exception {
		log.info("---TESTING---");
				
		
//		UUID userId = UUID.fromString("c7f91b1b-b276-4bc9-8304-26c90a1f4e85");
//		UUID calendarId = UUID.fromString("a75ecdbc-6704-4599-a03a-decf3a9b5080");
//		UUID eventId = UUID.fromString("b1fd834a-46fb-4ff3-b809-ca9e46e49957");
//		userService.addCalendarToUser(userId, calendarId);
//		calendarService.addEventToCalendar(calendarId, eventId);
	}
    
}

