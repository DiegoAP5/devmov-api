package casasegura.devmov.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Temperature>temperatures;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Smoke>smokes;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Movement>movements;
}