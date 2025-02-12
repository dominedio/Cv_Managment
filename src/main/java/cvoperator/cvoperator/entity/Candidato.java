package cvoperator.cvoperator.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "candidato")
public class Candidato {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @OneToOne
    @JoinColumn(name = "email", nullable = false, unique = true)
    private User user;

    @Column(name = "telefono",nullable = false)
    private String telefono;

    @Column(name = "esperienza",nullable = false)
    private String esperienza;

    @Column(name = "competenze",nullable = false)
    private String competenze;

    @Column(name = "istruzione",nullable = false)
    private String istruzione;

    @OneToOne(mappedBy = "cv", cascade = CascadeType.ALL)
    private Cv cv;

}
