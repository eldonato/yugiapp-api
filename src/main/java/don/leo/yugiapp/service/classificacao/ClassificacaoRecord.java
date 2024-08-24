package don.leo.yugiapp.service.classificacao;

import don.leo.yugiapp.data.entities.Classificacao;
import don.leo.yugiapp.service.jogador.JogadorRecord;
import don.leo.yugiapp.service.torneio.TorneioRecord;

public record ClassificacaoRecord(
        Integer id,
        Integer pontuacao,
        String deck,
        TorneioRecord torneio,
        JogadorRecord jogador
) {
    public ClassificacaoRecord(Classificacao classificacao) {
        this(
                classificacao.getId(),
                classificacao.getPontuacao(),
                classificacao.getDeck(),
                new TorneioRecord(classificacao.getTorneio()),
                new JogadorRecord(classificacao.getJogador())
        );
    }
}
