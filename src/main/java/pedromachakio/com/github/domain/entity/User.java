package pedromachakio.com.github.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "{field.user.username.mandatory}")
    private String username;

    @Column
    @NotEmpty(message = "{field.user.password.mandatory}")
    private String password;

    @Column
    private boolean admin; // se for false -> é um user

}
