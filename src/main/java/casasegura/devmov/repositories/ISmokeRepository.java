package casasegura.devmov.repositories;

import casasegura.devmov.entities.Smoke;
import casasegura.devmov.entities.projections.SmokeProjection;
import casasegura.devmov.entities.projections.TemperatureProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ISmokeRepository extends JpaRepository<Smoke, Long> {

    @Query(value = "select smokes.*, users.id as userId from smokes " +
            "inner join users on smokes.user_id = users.id " +
            "where smokes.id =:id", nativeQuery = true)
    SmokeProjection getSmokeById(Long id);

    @Query(value = "select smokes.*, users.id as userId from smokes "+
            "inner join users on smokes.user_id = users.id "+
            "where smokes.user_id =:userId and smokes.date =:date",nativeQuery = true)
    List<SmokeProjection> getSmokeByUserIdAnd(Long userId, LocalDate date);
}
