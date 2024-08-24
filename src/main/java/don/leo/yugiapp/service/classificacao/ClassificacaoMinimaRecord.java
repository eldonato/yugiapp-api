package don.leo.yugiapp.service.classificacao;

import don.leo.yugiapp.data.entities.Classificacao;

public record ClassificacaoMinimaRecord(
        Integer id,
        Integer pontuacao,
        String deck,
        Integer idTorneio,
        Integer idJogador,
        String nomeJogador
) {
    public ClassificacaoMinimaRecord(Classificacao classificacao) {
        this(
                classificacao.getId(),
                classificacao.getPontuacao(),
                classificacao.getDeck(),
                classificacao.getTorneio().getId(),
                classificacao.getJogador().getId(),
                classificacao.getJogador().getPessoa().getNome()
        );
    }
}
