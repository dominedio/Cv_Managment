package cvoperator.cvoperator.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role",columnDefinition = "varchar(20) default 'USER'")
    private String role;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @OneToOne(mappedBy = "candidato", cascade = CascadeType.ALL)
    private Candidato candidato;
}
