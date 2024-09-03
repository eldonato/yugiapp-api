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
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_seq")
    @SequenceGenerator(name = "pessoa_seq", sequenceName = "pessoa_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @OneToOne(mappedBy = "pessoa", orphanRemoval = true, fetch = FetchType.LAZY)
    private Jogador jogador;
}
