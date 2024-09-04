package don.leo.yugiapp.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jogador")
public class Jogador {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jogador_seq")
    @SequenceGenerator(name = "jogador_seq", sequenceName = "jogador_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "kossy")
    private String kossy;

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Jogador(Integer id) {
        this.id = id;
    }
}
