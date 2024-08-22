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
@Table(name = "liga")
public class Liga {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "liga_seq")
    @SequenceGenerator(name = "liga_seq", sequenceName = "liga_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dt_inicio")
    private LocalDate dataInicio;

    @Column(name = "dt_fim")
    private LocalDate dataFim;
}
