package casasegura.devmov.repositories;

import casasegura.devmov.entities.User;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User getUserByName(String name);
    @Query(value = "select users.* from users "+
            "where users.email = :email",nativeQuery = true)
    User getUserByEmail(String email);

    Optional<User> findByEmail(String username);
}
