package cvoperator.cvoperator.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "cv")
public class Cv {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = " candidato_id", nullable = false)
    private Candidato candidato;

    @Lob
    @Column(name = "documento_pdf", nullable = false)
    private byte[] cv;

    @Column(name = "data_creazione", nullable = false)
    private Date dataCreazione;

}
