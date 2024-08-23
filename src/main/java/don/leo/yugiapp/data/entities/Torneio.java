package don.leo.yugiapp.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "torneio")
public class Torneio {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "torneio_seq")
    @SequenceGenerator(name = "torneio_seq", sequenceName = "torneio_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_liga")
    private Liga liga;

    @ManyToOne
    @JoinColumn(name = "id_loja")
    private Loja loja;

    @Column(name = "dt_realizacao")
    private LocalDate data;

}
