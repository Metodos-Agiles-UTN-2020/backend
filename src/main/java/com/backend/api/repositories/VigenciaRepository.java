package com.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import com.backend.api.config.IdVigencia;
import com.backend.api.models.Vigencia;

@Component
public interface VigenciaRepository extends JpaRepository<Vigencia, IdVigencia> {

  @Query(
      value = "SELECT tiempo_vigencia FROM public.vigencia WHERE ?1 >= rango_inferior_edad AND ?1 <= rango_superior_edad",
      nativeQuery = true)
  Integer tiempoVigenciaByEdad(Integer edad);

  @Query(
      value = "SELECT tiempo_vigencia_primera_vez FROM public.vigencia WHERE ?1 >= rango_inferior_edad AND ?1 <= rango_superior_edad",
      nativeQuery = true)
  Integer tiempoVigenciaByEdadPrimeraVez(Integer edad);

}
