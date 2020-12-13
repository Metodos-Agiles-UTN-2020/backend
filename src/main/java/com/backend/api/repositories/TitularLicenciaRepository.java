package com.backend.api.repositories;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.backend.api.constants.TipoDocumento;
import com.backend.api.models.TitularLicencia;

@Component
public interface TitularLicenciaRepository extends JpaRepository<TitularLicencia, Long> {
  TitularLicencia findByNroDocumentoAndTipoDocumento(String nroDocumento,
      TipoDocumento tipoDocumento);


  @Modifying
  @Transactional
  @Query(value = "UPDATE titular_licencia SET tipo_documento = ?1, " + "nro_documento = ?2, "
      + "nombre = ?3, " + "apellido = ?4, " + "fecha_nacimiento = ?5, " + "domicilio = ?6, "
      + "grupo_sanguineo = ?7," + " factor_rh = ?8," + " donante = ?9,"
      + " foto = ?10 WHERE id = ?11", nativeQuery = true)
  void updateTitular(Integer tipo, String nroDocumento, String nombre, String apellido,
      Date fechaNacimiento, String direccion, Integer grupoSanguineo, Integer factorRH,
      Boolean donante, String foto, Long id);

}
