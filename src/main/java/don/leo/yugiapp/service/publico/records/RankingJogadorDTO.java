package don.leo.yugiapp.service.publico.records;

import don.leo.yugiapp.data.entities.Classificacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingJogadorDTO {

    private Integer idJogador;
    private String kossy;
    private String nome;
    @Builder.Default
    private Integer pontuacao = 0;
    @Builder.Default
    private Set<String> decks = new HashSet<>();

    public RankingJogadorDTO(Classificacao classificacao) {
        this.idJogador = classificacao.getJogador().getId();
        this.kossy = classificacao.getJogador().getKossy();
        this.nome = classificacao.getJogador().getPessoa().getNome();
        this.pontuacao = classificacao.getPontuacao();
        this.decks = new HashSet<>();
        if (StringUtils.hasText(classificacao.getDeck())) {
            decks.add(classificacao.getDeck());
        }
    }
}
