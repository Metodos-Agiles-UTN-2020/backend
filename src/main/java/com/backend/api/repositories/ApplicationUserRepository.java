package com.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.backend.api.models.ApplicationUser;

@Component
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
  ApplicationUser findByUsername(String username);

  ApplicationUser findByDni(String nroDocumento);

  @Modifying
  @Transactional
  @Query(
      value = "UPDATE application_user SET apellido=?4, dni=?5, mail=?6, nombre=?3, username=?2 WHERE id = ?1",
      nativeQuery = true)
  void updateUser(Long id, String username, String nombre, String apellido, String dni,
      String mail);
}
