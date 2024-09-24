 package com.te.lms.security.service;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.lms.entity.AppUser;
import com.te.lms.exception.UserIdNotAvailable;
import com.te.lms.security.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
  System.out.println("db accesed");
		AppUser appUser = appUserRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable(String.format("%s user not found", employeeId)));

		return (new User(appUser.getEmployeeId(), appUser.getPassword(), appUser.getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toSet())));
	}

}
