package don.leo.yugiapp.service.jogador;

import don.leo.yugiapp.data.entities.Jogador;
import don.leo.yugiapp.service.pessoa.PessoaRecord;

public record JogadorRecord(
        Integer id,
        String kossy,
        PessoaRecord pessoa
) {
    public JogadorRecord(Jogador jogador) {
        this(
                jogador.getId(),
                jogador.getKossy(),
                new PessoaRecord(jogador.getPessoa())
        );
    }
}
