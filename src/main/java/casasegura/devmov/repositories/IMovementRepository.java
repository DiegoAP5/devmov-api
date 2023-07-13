package casasegura.devmov.repositories;

import casasegura.devmov.entities.Movement;
import casasegura.devmov.entities.projections.MovementProjection;
import casasegura.devmov.entities.projections.SmokeProjection;
import casasegura.devmov.entities.projections.TemperatureProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovementRepository extends JpaRepository<Movement, Long> {

    @Query(value = "select movements.*, users.id as userId from movements " +
            "inner join users on movements.user_id = users.id " +
            "where movements.id =:id", nativeQuery = true)
    MovementProjection getMovementById(Long id);

    @Query(value = "select movements.*, users.id as userId from movements "+
            "inner join users on movements.user_id = users.id "+
            "where movements.user_id =:userId and movements.date =:date",nativeQuery = true)
    List<MovementProjection> getMovementByIdAndDate(Long userId, LocalDate date);
}
