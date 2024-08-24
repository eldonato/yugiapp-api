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
@Table(name = "classificacao")
public class Classificacao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classificacao_seq")
    @SequenceGenerator(name = "classificacao_seq", sequenceName = "classificacao_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_torneio")
    private Torneio torneio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogador")
    private Jogador jogador;

    @Column(name = "pontuacao")
    private int pontuacao;

    @Column(name="deck")
    private String deck;
}
