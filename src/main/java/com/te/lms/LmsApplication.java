package com.te.lms;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.te.lms.config.TwilioConfig;
import com.te.lms.entity.Admin;
import com.te.lms.entity.AppUser;
import com.te.lms.entity.Role;
import com.te.lms.entity.Technology;
import com.te.lms.repository.TechnologyRepository;
import com.te.lms.security.repository.AdminRepository;
import com.te.lms.security.repository.AppUserRepository;
import com.te.lms.security.repository.RoleRepository;
import com.twilio.Twilio;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
@PropertySource("file:C:/Users/Satish k/Desktop/Work/application.properties") 
public class LmsApplication {

	private final RoleRepository roleRepository;
	private final AppUserRepository appUserRepository;
	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;
	private final TechnologyRepository technologyRepository;
	private final TwilioConfig twilioConfig;


	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}
	
	//@PostConsrtuct has been used as the twilio will be configured at the server starting
	@PostConstruct
	public void initTwilio() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Optional<Role> roleName = roleRepository.findByRoleName("ROLE_EMPLOYEE");
			if (roleName.isEmpty()) {
				Admin admin = Admin.builder().adminName("Admin01").build();

				AppUser adminAppUser = AppUser.builder().employeeId("admin1").password(passwordEncoder.encode("a1@123"))
						.build();

				Role adminRole = Role.builder().roleName("ROLE_ADMIN").build();

				adminAppUser.setRoles(List.of(adminRole));

				adminRole.setAppUsers(List.of(adminAppUser));

				Role trainerRole = Role.builder().roleName("ROLE_MENTOR").build();
				
				Role employeeRole = Role.builder().roleName("ROLE_EMPLOYEE").build();

				roleRepository.save(trainerRole);
				roleRepository.save(employeeRole);
				adminRepository.save(admin);
				appUserRepository.save(adminAppUser);
			}
			Optional<Technology> technology = technologyRepository.findByTechnology("SQL");
			if(technology.isEmpty()) {
				technologyRepository.save(Technology.builder().technology("SQL").build());
				technologyRepository.save(Technology.builder().technology("CORE_JAVA").build());
				technologyRepository.save(Technology.builder().technology("ADVANCED_JAVA").build());
				technologyRepository.save(Technology.builder().technology("SPRING_CORE").build());
				technologyRepository.save(Technology.builder().technology("HIBERNATE_JPA").build());
				technologyRepository.save(Technology.builder().technology("SPRING_MVC").build());
				technologyRepository.save(Technology.builder().technology("SPRING_REST").build());
				technologyRepository.save(Technology.builder().technology("SPRING_BOOT").build());
				technologyRepository.save(Technology.builder().technology("SPRING_DATA").build());
				technologyRepository.save(Technology.builder().technology("SPRING_SECURITY").build());
				technologyRepository.save(Technology.builder().technology("REACT_JS").build());
				technologyRepository.save(Technology.builder().technology("ANGULAR_JS").build());
				technologyRepository.save(Technology.builder().technology("EXPRESS_JS").build());
				technologyRepository.save(Technology.builder().technology("NODE_JS").build());

			}
		};
	}

}
