package casasegura.devmov.repositories;

import casasegura.devmov.entities.Temperature;
import casasegura.devmov.entities.projections.TemperatureProjection;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ITemperatureRepository extends JpaRepository<Temperature, Long> {

    @Query(value = "select temperatures.*, users.id as userId from temperatures "+
            "inner join users on temperatures.user_id = users.id "+
            "where temperatures.user_id =:userId",nativeQuery = true)
    List<TemperatureProjection> listAllTemperatureByUserId(Long userId);

    @Query(value = "select temperatures.*, users.id as userId from temperatures "+
            "inner join users on temperatures.user_id = users.id "+
            "where temperatures.user_id =:userId and temperatures.date =:date and temperatures.time =:time",nativeQuery = true)
    List<TemperatureProjection> listTemperatureByDayAndUserId(Long userId, LocalDate date, LocalTime time);

    @Query(value = "select temperatures.temperature, users.id as userId from temperatures "+
            "inner join users on temperatures.user_id = users.id "+
            "where temperatures.user_id =:userId and temperatures.date =:date",nativeQuery = true)
    float[] getTemperatureByDate(Long userId, LocalDate date);

    @Query(value = "select temperatures.*, users.id as userId from temperatures "+
            "inner join users on temperatures.user_id = users.id "+
            "where temperatures.id =:id",nativeQuery = true)
    TemperatureProjection getTemperatureById(Long id);
}
