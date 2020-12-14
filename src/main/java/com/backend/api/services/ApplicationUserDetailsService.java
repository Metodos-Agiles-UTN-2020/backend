package com.backend.api.services;

import static java.util.Collections.emptyList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.backend.api.exceptions.NotFoundException;
import com.backend.api.models.ApplicationUser;
import com.backend.api.repositories.ApplicationUserRepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(ApplicationUserDetailsService.class);

  @Autowired
  private ApplicationUserRepository applicationUserRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
    if (applicationUser == null) {
      throw new UsernameNotFoundException(username);
    }
    return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
  }

  public ApplicationUser getUserDetails(String username) {
    return applicationUserRepository.findByUsername(username);
  }

  public ApplicationUser getUserByDNI(String nroDocumento) {

    ApplicationUser user = applicationUserRepository.findByDni(nroDocumento);

    if (user == null) {
      throw new NotFoundException();
    }

    return user;
  }

  public void updateUser(ApplicationUser updateUser) {

    applicationUserRepository.updateUser(updateUser.getId(), updateUser.getUsername(),
        updateUser.getNombre(), updateUser.getApellido(), updateUser.getDni(),
        updateUser.getMail());

  }

  public ApplicationUser saveUser(ApplicationUser newUser) {
    return applicationUserRepository.save(newUser);
  }

}
