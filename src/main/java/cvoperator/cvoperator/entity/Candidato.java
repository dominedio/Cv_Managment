package cvoperator.cvoperator.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Builder
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

    @ElementCollection
    //@CollectionTable(name = "candidato_competenze", joinColumns = @JoinColumn(name = "candidato_id"))
    @Builder.Default
    @Column(name = "competenze",nullable = false)
    private List<String> competenze = new ArrayList<>();

    @Column(name = "istruzione",nullable = false)
    private String istruzione;

    @OneToOne(mappedBy = "cv", cascade = CascadeType.ALL)
    private Cv cv;

    public Candidato() {

    }
}
